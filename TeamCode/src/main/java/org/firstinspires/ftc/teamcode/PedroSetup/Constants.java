package org.firstinspires.ftc.teamcode.PedroSetup;

import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.follower.Follower;
import com.pedropathing.controllers.Controller;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    // 1. Drivetrain Configurations
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("left_front_drive");
        c.frontRightName.set("right_front_drive");
        c.backLeftName.set("left_back_drive");
        c.backRightName.set("right_back_drive");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });

    // 2. Pinpoint Localizer Configurations
    /*public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-5.3008714811069755);
        c.yPodOffset.set(-1.5324550538551152);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });*/
    // 3. Official Pedro 3 Foresight Config

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-5.593718881682149);
        c.yPodOffset.set(-1.9688130175973486);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });


   /*public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1623026864273539);
                Controller secondaryTranslationalForward = Controller.proportional(0.059966505827253797);
                Controller primaryTranslationalLateral = Controller.proportional(0.20661532319492568);
                Controller secondaryTranslationalLateral = Controller.proportional(0.07633884105741008);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01598651331906082));
                c.brake.set(Controller.proportionalFeedforward(0.013588536321201697));

                c.headingFeedback.set(Controller.proportional(2.829115661212107));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.052602496358675796, 0.0021209738499533173));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07538823647229365, 0.062367542092334055));
                c.quadraticBrakeCoefficients.set(Matrix.diag(8.909892345736001E-4, 0.0010589961735868376));

                c.maxAchievableForwardVelocity.set(57.876914407129775);
                c.maxAchievableStrafeVelocity.set(48.97760396439227);
                c.naturalForwardDeceleration.set(32.43512295091769);
                c.naturalStrafeDeceleration.set(57.4657369316057);
            }
    );

    */

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1803085393095732)
                        ;
                Controller secondaryTranslationalForward = Controller.proportional(0.0666191873419838)
                        ;
                Controller primaryTranslationalLateral = Controller.proportional(0.23456878959129634)
                        ;
                Controller secondaryTranslationalLateral = Controller.proportional(0.08666689996049051)
                        ;
                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward))
                        ;
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral))
                        ;
                c.coast.set(Controller.proportionalFeedforward(0.0160016780774871))
                        ;
                c.brake.set(Controller.proportionalFeedforward(0.013601426365864033))
                        ;
                c.headingFeedback.set(Controller.proportional(2.79559949398457))
                        ;
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04763209540841572, 0.003580084951613528))
                        ;
                c.linearBrakeCoefficients.set(Matrix.diag(0.08083925584267182, 0.05400875491994723))
                        ;
                c.quadraticBrakeCoefficients.set(Matrix.diag(5.417892237420149E-4, 0.0010420440217303408))
                        ;
                c.maxAchievableForwardVelocity.set(63.391005567990646)
                        ;
                c.maxAchievableStrafeVelocity.set(54.09117207878694)
                        ;
                c.naturalForwardDeceleration.set(36.59463238015034)
                        ;
                c.naturalStrafeDeceleration.set(65.6226196196684)
                        ;
                c.cosineScale.set(true)
                        ;
                c.maxPathSpeed.set(0.8)
                        ;
            }
    );

   /* public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1694513586468517);
                Controller secondaryTranslationalForward = Controller.proportional(0.06260774919631855);
                Controller primaryTranslationalLateral = Controller.proportional(0.20163033737423636);
                Controller secondaryTranslationalLateral = Controller.proportional(0.07449702199793971);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.015833708712463));
                c.brake.set(Controller.proportionalFeedforward(0.01345865240559355));

                c.headingFeedback.set(Controller.proportional(2.9457817370491615));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.049915159942017694, 0.0029192321844269827));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08374162374712701, 0.0982845631101032));
                c.quadraticBrakeCoefficients.set(Matrix.diag(1.9072159374757642E-4, 2.4200860891371257E-4));

                c.maxAchievableForwardVelocity.set(64.61800441529984);
                c.maxAchievableStrafeVelocity.set(55.20842717763734);
                c.naturalForwardDeceleration.set(36.09093797090695);
                c.naturalStrafeDeceleration.set(59.6843741166468);
            }
    );
*/
    // Autonomous Mode Helper (Takes exactly 3 items in Pedro 3)
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}