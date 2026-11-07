package org.firstinspires.ftc.teamcode.Auton;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.PedroSetup.Constants;

@Autonomous(name = "AutoPath", group = "Autonomous")
@Configurable
public class Auton_Code_Shivani2 extends OpMode {

    private TelemetryManager panelsTelemetry;
    public Follower follower;

    private final PoseFactory p = PoseFactory.degrees();

    private int pathState = 0;
    private ElapsedTime pathTimer;

    private Paths paths;

    @Override
    public void init() {

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        follower = Constants.create(hardwareMap);

        // Starting position
        follower.setPose(
                p.of(10.000, 105.000, -63.048)
        );

        pathTimer = new ElapsedTime();

        // Build paths
        paths = new Paths(follower);
    }

    @Override
    public void loop() {

        // Update Pedro Pathing
        follower.update();

        // Update autonomous state machine
        pathState = autonomousPathUpdate();

        // Telemetry
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.pose().x());
        panelsTelemetry.debug("Y", follower.pose().y());
        panelsTelemetry.debug("Heading", follower.pose().heading());

        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

        private static final PoseFactory p = PoseFactory.degrees();

        public Path driveToShoot;
        public Path line2;
        public Path line3;

        public Paths(Follower follower) {

            driveToShoot = curve(
                    p.of(10.000, 105.000, 0.0),
                    p.of(40.000, 46.000, 0.0),
                    p.of(46.500, 134.000, 0.0)
            ).tangent();

            line2 = line(
                    p.of(46.500, 134.000, 0.0),
                    p.of(10.000, 106.746, 0.0)
            ).facingPoint(
                    p.of(71.739, 71.739, 0.0)
            );

            line3 = line(
                    p.of(10.000, 106.746, 0.0),
                    p.of(10.000, 105.000, 0.0)
            ).tangent();
        }
    }

    public int autonomousPathUpdate() {

        switch (pathState) {

            case 0:
                follower.follow(paths.driveToShoot);
                setPathState(1);
                break;

            case 1:
                if (!follower.isBusy()) {
                    setPathState(2);
                }
                break;

            case 2:
                follower.follow(paths.line2);
                setPathState(3);
                break;

            case 3:
                if (!follower.isBusy()) {
                    setPathState(4);
                }
                break;

            case 4:
                follower.follow(paths.line3);
                setPathState(5);
                break;

            case 5:
                if (!follower.isBusy()) {
                    setPathState(6);
                }
                break;

            case 6:
                requestOpModeStop();
                pathState = -1;
                break;
        }

        return pathState;
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.reset();
    }
}