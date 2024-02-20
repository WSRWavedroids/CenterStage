package org.firstinspires.ftc.teamcode.Autonomous.Blue.BlueLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Far Place")
    public class BlueFarPlace extends AutonomousPLUS {

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
                        telemetry.addData("Its time to", "Gatekeep, Gasslight Girlboss");
                        telemetry.addData("Ur gonna do", "great");
                    }
                    telemetry.update();

                    sleep(20);
                }
            }
            //Start and position yellow
            waitForStart();
            telemetry.addData(currentPosition,"here now");
            robot.closeClaw();
            prepareNextAction(300);
            sleepTime = 175;
            prepareNextAction(300);
            //Branches here
            moveRobotForward(150, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Left");
                speed = .70;
                moveRobotForward(1030, 2);
                turnRobotLeft(1060, 2);
                moveRobotForward(555, 2);
                prepareNextAction(400);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(700);
                speed = .60;
                moveRobotBackward(1000, 2);
            }


            else if(target == "Center")
            {
                telemetry.addData("Going to", "Center");
                robot.rotateArmDown();
                moveRobotRight(100, 2);
                moveRobotForward(1600, 50);
                prepareNextAction(200);//new
                sleepTime = 100;
                speed = .75;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(850, 1);
            }

            else if(target == "Right Zone")
            {
                telemetry.addData("Going to", "Right");
                moveRobotForward(950, 2);
                moveRobotRight(450, 2);
                prepareNextAction(200);//new
                moveLift("Up", .55);
                moveRobotForward(450,2);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(850, 1);

            } else {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Left");
                speed = .70;
                moveRobotForward(1030, 2);
                turnRobotLeft(1060, 2);
                moveRobotForward(555, 2);
                prepareNextAction(400);
                robot.openSecondaryClaw();
                robot.rotateArmUp();
                prepareNextAction(700);
                speed = .60;
                moveRobotBackward(1000, 2);

            }
        }
    }

