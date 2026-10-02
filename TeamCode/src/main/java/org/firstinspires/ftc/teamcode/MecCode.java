package org.firstinspires.ftc.teamcode;
//importing all of our code
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class MecCode extends LinearOpMode {
    // All motors in starting position which is null or zero.
    public DcMotor frontLeft = null;

    public DcMotor bl = null;

    public DcMotor fr = null;

    public DcMotor br = null;

    public DcMotor ShooterMotor = null;

    public Servo RightFeeder = null;

    public Servo LeftFeeder = null;

    public DcMotor IntakeMotor = null;

    public CRServo windmill = null;

    // public allows you to take variables from other codes
    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "left_front_drive");
        bl = hardwareMap.get(DcMotor.class, "left_back_drive");
        fr = hardwareMap.get(DcMotor.class, "right_front_drive");
        br = hardwareMap.get(DcMotor.class, "right_back_drive");
        ShooterMotor = hardwareMap.get(DcMotor.class, "ShooterMotor");
        IntakeMotor = hardwareMap.get(DcMotor.class, "intake");
        RightFeeder = hardwareMap.get(Servo.class, "right_intake_servo");
        LeftFeeder = hardwareMap.get(Servo.class, "left_intake_servo");
        windmill = hardwareMap.get(CRServo.class, "windmill");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        LeftFeeder.setDirection(Servo.Direction.REVERSE);
// double means numbers
        double driveMode = 0;
        double maxPower = 0.5;
//only play code when start is pressed
        waitForStart();
// this is a loop
        while (opModeIsActive()) {

// turn = rotation, powerst= side to side ,power= move forward or backward
            double turn = gamepad1.right_stick_x;
            double powerst = gamepad1.left_stick_x;
            double power = -gamepad1.left_stick_y;

            double frontLeftPower = 0;
            double frPower = 0;
            double blPower = 0;
            double brPower = 0;
//defining gamepad controls
            if (gamepad1.dpad_up) {
                driveMode = 0;
            }


            if (gamepad1.dpad_left) {
                driveMode = 3;
            }


// Set motor directions and calculate powers
            if (driveMode == 0) {

                frontLeft.setDirection(DcMotor.Direction.FORWARD);
                bl.setDirection(DcMotor.Direction.FORWARD);
                fr.setDirection(DcMotor.Direction.FORWARD);
                br.setDirection(DcMotor.Direction.REVERSE);

                frontLeftPower = power + powerst + turn;
                blPower = power - powerst + turn;
                frPower = power - powerst - turn;
                brPower = power + powerst - turn;

            }



            if (driveMode == 1) {

                frontLeft.setDirection(DcMotor.Direction.REVERSE);
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
                blPower = -power - powerst -turn;
                frPower = -power - powerst + turn;
                brPower = -power + powerst - turn;

            }

//CHANGE

            frontLeftPower = power + powerst + turn;
            blPower = power - powerst + turn;
            frPower = power - powerst - turn;
            brPower = power + powerst - turn;

//this allows it to slow down without immediately braking
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
                RightFeeder.setPosition(1);
                LeftFeeder.setPosition(1);
            }


            if (gamepad1.bWasReleased()) {
                RightFeeder.setPosition(0);
                LeftFeeder.setPosition(0);
            }



// boolean is true or false
            boolean increase = gamepad1.rightBumperWasPressed();
            boolean decrease = gamepad1.leftBumperWasPressed();


            if (decrease){
                maxPower = maxPower - 0.05;
            } else {
                if (increase){
                    maxPower = maxPower + 0.05;
                }
            }


            ShooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            ShooterMotor.setPower(gamepad1.left_trigger*maxPower);

            if (gamepad1.dpadRightWasPressed()) {
                windmill.setPower(1);
            }

            if (gamepad1.dpadRightWasReleased()) {
                windmill.setPower(0);
            }







// telemetry means this code will pop up in the driver hub
            telemetry.addData("shootspeed", maxPower*gamepad1.left_trigger);
            telemetry.addData("Maxpower", maxPower);
            telemetry.addData("Right Trigger", gamepad1.right_trigger);
            telemetry.addData("Left Trigger", gamepad1.left_trigger);
            telemetry.update();




        }
    }

}







