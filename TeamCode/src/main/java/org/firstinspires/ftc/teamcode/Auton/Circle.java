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

@Autonomous
public class Circle extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(0, 0, 0);

    private final Pose lowestPoint = poseFactory.of(15, 0, 90);

    private final Pose highestPoint = poseFactory.of(15, 30, -90);

    private  final Pose end = poseFactory.of(30, 0, 180);

    private final Pose rightPoint = poseFactory.of(30, 15, 180);

    private  final Pose leftPoint = poseFactory.of(0, 15, 0);

    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3())
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

        return line(start, leftPoint).tangent();
    }

    public Path path2() {
        return through(leftPoint, highestPoint, rightPoint, lowestPoint, leftPoint, highestPoint, rightPoint).tangent();
    }

    public Path path3() {
        return line(rightPoint, end).tangent();
    }
}





