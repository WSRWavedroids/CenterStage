package org.firstinspires.ftc.teamcode.Autonomous.Red.RedLive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Red Close Place")
    public class redClosePlace extends AutonomousPLUS {

        public TensorFlow TF = new TensorFlow();
        public String currentPosition;
        public String target;

        public void runOpMode() {

            super.runOpMode();
            if (opModeInInit()) {
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
            //Start
            waitForStart();
            telemetry.addData(currentPosition,"here now");
            robot.closeClaw();
            robot.closeSecondaryClaw();
            prepareNextAction(300);
            moveRobotLeft(100, 2);
            telemetry.addData(currentPosition,"still here");

            if(target == "Left Zone")
            {
                telemetry.addData("Going to", "Left");
                moveRobotForward(1150, 2);
                turnRobotLeft(1070, 2);
                moveRobotForward(570, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1470, 2);
            }


            else if(target == "Center")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Center");
                moveRobotForward(1780, 50);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(750, 10);
                moveLift("Down", .55);

            }

            else if(target == "Right Zone")
            {
                robot.rotateArmDown();
                telemetry.addData("Going to", "Right");
                moveRobotRight(640, 2);
                moveRobotForward(1375, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(650, 2);
                moveRobotLeft(900, 2);
                moveRobotBackward(900, 2);
                robot.openClaw();

            } else {
                telemetry.addData("Going to", "Left");
                moveRobotForward(1150, 2);
                turnRobotLeft(1070, 2);
                moveRobotForward(570, 2);
                prepareNextAction(200);
                sleepTime = 175;
                moveLift("Up", .55);
                prepareNextAction(200);
                robot.openSecondaryClaw();
                prepareNextAction(400);
                moveRobotBackward(1470, 2);
            }
        }
    }

