package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DriveTrain extends LinearOpMode {

    public void runOpMode() {

        DcMotor fl = hardwareMap.get(DcMotor.class, "front_left_drive");
        DcMotor bl = hardwareMap.get(DcMotor.class, "back_left_drive");
        DcMotor fr = hardwareMap.get(DcMotor.class, "front_right_drive");
        DcMotor br = hardwareMap.get(DcMotor.class, "back_right_drive");

        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        DcMotor shooterMotor = hardwareMap.get(DcMotor.class, "shooter_motor");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        double multiplier = 1.0;

        waitForStart();

        while (opModeIsActive()) {

            // Drive
            double power = -gamepad1.right_stick_y;
            double powerst = gamepad1.right_stick_x;
            double turn = gamepad1.left_stick_x;

            double flPower = power + powerst + turn;
            double blPower = power - powerst + turn;
            double frPower = power - powerst - turn;
            double brPower = power + powerst - turn;

            
            fl.setPower(flPower);
            bl.setPower(blPower);
            fr.setPower(frPower);
            br.setPower(brPower);


            // Shooter and intake
            shooterMotor.setPower(gamepad1.right_trigger);
            intakeMotor.setPower(gamepad1.left_trigger);


            telemetry.addData("Right Trigger", gamepad1.right_trigger);
            telemetry.addData("Left Trigger", gamepad1.left_trigger);
            telemetry.update();
        }
    }
}