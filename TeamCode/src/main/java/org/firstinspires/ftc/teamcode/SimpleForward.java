package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Just Move Forward")
public class SimpleForward extends LinearOpMode {

    // STEP 1: Name your two main drive motors
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    @Override
    public void runOpMode() {

        // STEP 2: Link those names to the actual robot hardware ports
        leftMotor = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_front_drive");

        // STEP 3: Flip one motor so they pull the robot in the same direction
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);

        // Wait until you press the INIT and PLAY buttons on the driver phone
        waitForStart();

        // STEP 4: While the game is playing, turn the motors on!
        while (opModeIsActive()) {
            leftMotor.setPower(0.3);  // 30% speed
            rightMotor.setPower(0.3); // 30% speed
        }
    }
}