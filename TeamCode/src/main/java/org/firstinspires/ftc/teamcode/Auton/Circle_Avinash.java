/* ============================================================= *
 *                 Turtle Tracer — Auto-Generated                *
 *                                                               *
 *  Version: 2.7.0.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Auton;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.api.Paths.path;

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

@Autonomous
@Configurable // Panels
public class Circle_Avinash extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pathing follower instance
    private final PoseFactory p = PoseFactory.degrees();
    private int pathState; // Current autonomous path state (state machine)
    private ElapsedTime pathTimer; // Timer for path state machine
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        // ...
        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        follower = Constants.create(hardwareMap);
        follower.setPose(p.of(9.000, 25.000, 0.000));

        pathTimer = new ElapsedTime();
        paths = new Paths(follower); // Build paths
    }

    @Override
    public void loop() {
        follower.update(); // Update follower
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.pose().x());
        panelsTelemetry.debug("Y", follower.pose().y());
        panelsTelemetry.debug("Heading", follower.pose().heading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

        private static final PoseFactory p = PoseFactory.degrees();
        public Path DriveToShoot;
        public Path line2;
        public Path line3;
        public Path line4;

        public Paths(Follower follower) {
            DriveToShoot = line(
                    p.of(9.000, 25.000, 0.0),
                    p.of(60.000, 25.000, 0.0)
            ).tangent();

            line2 = curve(
                    p.of(60.000, 25.000, 0.0),
                    p.of(123.694, 33.909, 0.0),
                    p.of(93.064, 95.154, 0.0),
                    p.of(54.256, 77.359, 0.0)
            ).tangent();

            line3 = curve(
                    p.of(54.256, 77.359, 0.0),
                    p.of(13.717, 62.411, 0.0),
                    p.of(31.667, 18.897, 0.0),
                    p.of(62.782, 25.000, 0.0)
            ).tangent();

            line4 = line(
                    p.of(62.782, 25.000, 0.0),
                    p.of(97.500, 25.000, 0.0)
            ).tangent();
        }
    }

    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.follow(paths.DriveToShoot);
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
                follower.follow(paths.line4);
                setPathState(7);
                break;
            case 7:
                if (!follower.isBusy()) {
                    setPathState(8);
                }
                break;
            case 8:
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
