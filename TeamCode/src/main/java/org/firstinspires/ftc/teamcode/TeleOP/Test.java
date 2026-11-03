package org.firstinspires.ftc.teamcode.TeleOP;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;

//import com.pedropathing.follower.Follower;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.api.PoseFactory;
//import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.pedropathing.paths.interpolator.Interpolator;
import static com.pedropathing.ivy.groups.Groups.sequential;
import org.firstinspires.ftc.teamcode.PedroSetup.Constants;
import org.firstinspires.ftc.teamcode.PedroSetup.Constants;

@TeleOp
public class Test extends OpMode {

    public int ShooterTargetRPM = 2700;
    public int ShooterMinimumRPM = ShooterTargetRPM - 80;

    public int LAUNCHER_TARGET_VELOCITY = (ShooterTargetRPM / 60) * 28;
    public int LAUNCHER_MIN_VELOCITY = (ShooterMinimumRPM / 60) * 28;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(0, 0, 0);
    public DcMotor frontLeft;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor br;
    public DcMotorEx ShooterMotor;
    public CRServo RightFeeder;
    public CRServo LeftFeeder;
    public DcMotor IntakeMotor;
    public CRServo windmill;

    public GoBildaPinpointDriver pinpoint;

    private Follower follower;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "left_front_drive");
        bl = hardwareMap.get(DcMotor.class, "left_back_drive");
        fr = hardwareMap.get(DcMotor.class, "right_front_drive");
        br = hardwareMap.get(DcMotor.class, "right_back_drive");
        ShooterMotor = hardwareMap.get(DcMotorEx.class, "ShooterMotor");
        IntakeMotor = hardwareMap.get(DcMotor.class, "intake");
        RightFeeder = hardwareMap.get(CRServo.class, "right_intake_servo");
        LeftFeeder = hardwareMap.get(CRServo.class, "left_intake_servo");
        windmill = hardwareMap.get(CRServo.class, "windmill");
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "a");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        LeftFeeder.setDirection(CRServo.Direction.FORWARD);
        RightFeeder.setDirection(CRServo.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ShooterMotor.setMode(RUN_USING_ENCODER);
        ShooterMotor.setPIDFCoefficients(RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));

        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {

        follower.setPose(poseFactory.of(0,0,0));

        int LAUNCHER_TARGET_VELOCITY = (ShooterTargetRPM / 60) * 28;
        int LAUNCHER_MIN_VELOCITY = (ShooterMinimumRPM / 60) * 28;

        if (gamepad1.dpadUpWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM + 1;
        } else if (gamepad1.dpadDownWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM + 30;
        } else if (gamepad1.dpadRightWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM - 30;
        } else if (gamepad1.dpadLeftWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM - 1;
        }

        ShooterMinimumRPM = ShooterTargetRPM - 80;

        LeftFeeder.setDirection(CRServo.Direction.FORWARD);
        RightFeeder.setDirection(CRServo.Direction.REVERSE);


        double maxPower = 0.5;

        double turn = gamepad1.right_stick_x;
        double powerst = gamepad1.left_stick_x;
        double power = -gamepad1.left_stick_y;

        double head = follower.pose().heading();
        double x = follower.pose().x();
        double y = follower.pose().y();
        double targetY = 20;
        double targetX = 0;
        double theta = Math.atan2(targetY - y, targetX - x);

        telemetry.addData("heading", head);

        if (gamepad1.left_bumper && turn == 0) {
            turn = theta - head;
        }

        double frontLeftPower = 0;
        double frPower = 0;
        double blPower = 0;
        double brPower = 0;


            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.REVERSE);

            frontLeftPower = power + powerst + turn;
            blPower = power - powerst + turn;
            frPower = power - powerst - turn;
            brPower = power + powerst - turn;




        double slow = 1.2 + -gamepad1.right_trigger;

        frontLeft.setPower(frontLeftPower * slow);
        bl.setPower(blPower * slow);
        fr.setPower(frPower * slow);
        br.setPower(brPower * slow);

        if (gamepad1.a) {
            IntakeMotor.setPower(1);
        } else {
            IntakeMotor.setPower(0);
        }

        if (gamepad1.bWasPressed()) {
            RightFeeder.setPower(1);
            LeftFeeder.setPower(1);
        }

        if (gamepad1.bWasReleased()) {
            RightFeeder.setPower(0);
            LeftFeeder.setPower(0);
        }



        if (ShooterMotor.getVelocity() >= LAUNCHER_MIN_VELOCITY) {
            windmill.setPower(1);
            ShooterMotor.setVelocity(LAUNCHER_TARGET_VELOCITY + 10);
        } else {
            windmill.setPower(0);
        }

        if (gamepad1.right_bumper) {
            ShooterMotor.setVelocity(LAUNCHER_TARGET_VELOCITY);
            gamepad1.setLedColor(0, 225, 0, 1000);
            gamepad1.rumble(1000);
        } else {
            ShooterMotor.setVelocity(0);
        }

        follower.update();
        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("heading", head);
        telemetry.addData("shootspeed", maxPower * gamepad1.left_trigger);
        telemetry.addData("Maxpower", maxPower);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.addData("TargetShootSpeed", LAUNCHER_TARGET_VELOCITY);
        telemetry.addData("ActualShootSpeed", ShooterMotor.getVelocity());
        telemetry.addData("target angle", theta );
        telemetry.addData("turn", turn);
        telemetry.update();
    }
}
