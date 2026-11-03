package org.firstinspires.ftc.teamcode.Auton;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PedroSetup.Constants;

@Autonomous(name = "TestTestAutoPath.", group = "Autonomous")
public class AutoSuccess extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(0, 0, 0);
    private final Pose point1 = poseFactory.of(50, 0, 90);

    private  final Pose point2 = poseFactory.of(50, 20, 180);

    private  final Pose point3 = poseFactory.of(0, 20, 270);

    private  final Pose point4 = poseFactory.of(0, 0, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4())




        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", Math.round(follower.pose().x()));
            telemetry.addData("y", Math.round(follower.pose().y()));
            telemetry.addData("heading", Math.round(Math.toDegrees(follower.pose().heading())));

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    public Path path1() {
        return line(start, point1).linear(start, point1);
    }

    public Path path2() {
        return line(point1, point2).tangent();
    }

    public Path path3() {
        return line(point2, point3).tangent();
    }


    public Path path4() {
        return line(point3, point4).tangent();
    }




}




