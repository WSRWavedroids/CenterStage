package org.firstinspires.ftc.teamcode.Autonomous;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
@Disabled
@Autonomous(group = "Stage", name = "ArmTest")
public class ArmTestThing extends AutonomousPLUS {

    @Override

    public void runOpMode()
    {

        super.runOpMode();

        //Do this to pass inspection.
        waitForStart();

        {
            sleepTime = 250;
            moveLift("Up", .55);//
            prepareNextAction(300);
            robot.rotateArmUp();
            prepareNextAction(800);
            robot.rotateArmDown();
            prepareNextAction(800);

        }

    }
}
