package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Close Place")
public class BlueClosePlace extends AutonomousPLUS {

    public TensorFlow TF = new TensorFlow();
    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();
        if (opModeInInit()) {
            //Needs TF reference
            TF.initTfod(robot.hardwareMap);
            while (opModeInInit()) {
                currentPosition = TF.position(TF.tfod);
                telemetry.addData(target, "Its here");

                if (currentPosition != null) {
                    target = currentPosition;
                }
                telemetry.update();
                sleep(20);
            }
        }
        //Start and position yellow
        waitForStart();
        telemetry.addData(currentPosition,"here now");
        robot.claw.closeClaw();
        robot.claw.closeSecondaryClaw();
        prepareNextAction(300);
        prepareNextAction(300);
        //Branches here
        StrafeFromOdometry(150,"Up",2,2);
        telemetry.addData(currentPosition,"still here");
        speed = .75;

        if(target == "Left Zone")
        {
            telemetry.addData("Going to", "Left");
            robot.arm.rotateArmUp();
            StrafeFromOdometry(700,"Left",2,2);
            StrafeFromOdometry(1100,"Up",2,2);
            prepareNextAction(150);
            robot.claw.openSecondaryClaw();
            prepareNextAction(150);
            StrafeFromOdometry(600,"Down",2,2);
        }


        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            robot.arm.rotateArmDown();
            StrafeFromOdometry(100,"Left",2,2);
            StrafeFromOdometry(1600,"Up",2,2);
            prepareNextAction(200);//new
            speed = .75;
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(750,"Down",2,2);
        }

        else if(target == "Right Zone")
        {
            telemetry.addData("Going to", "Right ");
            speed = 0.5;
            StrafeFromOdometry(100,"Left",0,2);
            StrafeFromOdometry(1000,"Up",2,1);
            TurnFromOdometry(90,"Right",2);
            StrafeFromOdometry(100,"Left",0,1);
            StrafeFromOdometry(565,"Up",1,1);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            speed = .75;
            StrafeFromOdometry(1450,"Down",2,1);


        } else {
            telemetry.addData("Going to", "Left");
            robot.arm.rotateArmUp();
            StrafeFromOdometry(700,"Left",2,2);
            StrafeFromOdometry(1100,"Up",2,2);
            prepareNextAction(150);
            robot.claw.openSecondaryClaw();
            prepareNextAction(150);
            StrafeFromOdometry(600,"Down",2,2);

        }
    }
}
