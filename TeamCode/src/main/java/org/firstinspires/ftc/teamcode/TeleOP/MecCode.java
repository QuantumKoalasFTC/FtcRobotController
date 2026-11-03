package org.firstinspires.ftc.teamcode.TeleOP;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;

import com.pedropathing.api.PoseFactory;

import com.pedropathing.math.Pose;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class   MecCode extends OpMode {

    public int ShooterTargetRPM = 2700;
    public int ShooterMinimumRPM = ShooterTargetRPM - 80;

    public int LAUNCHER_TARGET_VELOCITY = (ShooterTargetRPM / 60) * 28;
    public int LAUNCHER_MIN_VELOCITY = (ShooterMinimumRPM / 60) * 28;

    public DcMotor frontLeft = null;
    public DcMotor bl = null;
    public DcMotor fr = null;
    public DcMotor br = null;
    public DcMotorEx ShooterMotor = null;
    public CRServo RightFeeder = null;
    public CRServo LeftFeeder = null;
    public DcMotor IntakeMotor = null;
    public CRServo windmill = null;


    public GoBildaPinpointDriver pinpoint = null;

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
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterMotor.setMode(RUN_USING_ENCODER);
        ShooterMotor.setPIDFCoefficients(RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
    }

    @Override
    public void loop() {


        if (gamepad1.dpadUpWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM + 1;
        } else if (gamepad1.dpadDownWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM + 30;
        } else if (gamepad1.dpadRightWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM - 30;
        } else if (gamepad1.dpadLeftWasPressed()) {
            ShooterTargetRPM = ShooterTargetRPM - 1;
        }

        int ShooterMinimumRPM = ShooterTargetRPM - 80;

        int LAUNCHER_TARGET_VELOCITY = (ShooterTargetRPM / 60) * 28;
        int LAUNCHER_MIN_VELOCITY = (ShooterMinimumRPM / 60) * 28;



        LeftFeeder.setDirection(CRServo.Direction.FORWARD);
        RightFeeder.setDirection(CRServo.Direction.REVERSE);

        double driveMode = 0;
        double maxPower = 0.5;

        double turn = gamepad1.right_stick_x;
        double powerst = gamepad1.left_stick_x;
        double power = -gamepad1.left_stick_y;

        double frontLeftPower = 0;
        double frPower = 0;
        double blPower = 0;
        double brPower = 0;


            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.REVERSE);




       //if (driveMode == 1) {
     /*       frontLeft.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.REVERSE);
            fr.setDirection(DcMotor.Direction.REVERSE);
            br.setDirection(DcMotor.Direction.FORWARD);

            frontLeftPower = power + powerst - turn;
            blPower = power - powerst - turn;
            frPower = power - powerst + turn;
            brPower = power + powerst + turn;
        }

        if (driveMode == 2) {
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.FORWARD);

            frontLeftPower = -power + powerst - turn;
            blPower = -power - powerst + turn;
            frPower = -power - powerst - turn;
            brPower = -power + powerst + turn;
     }

       if (driveMode == 3) {
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            bl.setDirection(DcMotor.Direction.REVERSE);
            fr.setDirection(DcMotor.Direction.REVERSE);
            br.setDirection(DcMotor.Direction.REVERSE);

            frontLeftPower = -power + powerst + turn;
            blPower = -power - powerst - turn;
            frPower = -power - powerst + turn;
            brPower = -power + powerst - turn;
        } */

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

        boolean increase = gamepad1.rightBumperWasPressed();
        boolean decrease = gamepad1.leftBumperWasPressed();



        if (ShooterMotor.getVelocity() >= LAUNCHER_TARGET_VELOCITY) {
            windmill.setPower(1);
            ShooterMotor.setVelocity(LAUNCHER_TARGET_VELOCITY + 10);
        } else {
            windmill.setPower(0);
            ShooterMotor.setVelocity(LAUNCHER_TARGET_VELOCITY);
        }

        if (gamepad1.right_bumper) {
            ShooterMotor.setVelocity(LAUNCHER_TARGET_VELOCITY);
            gamepad1.setLedColor(0, 225, 0, 1000);
            gamepad1.rumble(1000);
        } else {

        }


        telemetry.addData("shootspeed", maxPower * gamepad1.left_trigger);
        telemetry.addData("Maxpower", maxPower);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.addData("TargetShootSpeed", ShooterTargetRPM);
        telemetry.addData("ActualShootSpeed", ShooterMotor.getVelocity());
        telemetry.addData("turn", String.valueOf(turn));
        telemetry.update();
    }
}