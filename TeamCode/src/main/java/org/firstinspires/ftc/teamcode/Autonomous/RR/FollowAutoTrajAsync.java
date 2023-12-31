package org.firstinspires.ftc.teamcode.Autonomous.RR;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.RR.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class FollowAutoTrajAsync extends AutonomousPLUS {

    // This enum defines our "state"
    // This is essentially just defines the possible steps our program will take
    public enum State {
        BASE_TRAJ,      // First, follow the basic instructions
        LEFT_TRAJ,      // If left, then do this
        CENTER_TRAJ,    // If center, then do this
        RIGHT_TRAJ,     // If right, then do this
        IDLE            // Our bot will enter the IDLE state when done
    }

    // We define the current state we're on
    // Default to IDLE
    State currentState = State.IDLE;

    public void executeFATA(SampleMecanumDrive drive, Trajectory baseTraj, Trajectory leftTraj, Trajectory centerTraj, Trajectory rightTraj, Pose2d startPose){

        // Set initial pose
        drive.setPoseEstimate(startPose);

        if (isStopRequested()) return;

    // Set the current state to TRAJECTORY_1, our first step
    // Then have it follow that trajectory
    // Make sure you use the async version of the commands
    // Otherwise it will be blocking and pause the program here until the trajectory finishes
        currentState = State.BASE_TRAJ;
        drive.followTrajectoryAsync(baseTraj);

        while (opModeIsActive() && !isStopRequested()) {
        // Our state machine logic
        // You can have multiple switch statements running together for multiple state machines
        // in parallel. This is the basic idea for subsystems and commands.

        // We essentially define the flow of the state machine through this switch statement
        switch (currentState) {
            case BASE_TRAJ:
                // Check if the drive class isn't busy
                // `isBusy() == true` while it's following the trajectory
                // Once `isBusy() == false`, the trajectory follower signals that it is finished
                // We move on to the next state
                // Make sure we use the async follow function
                if (!drive.isBusy()) {

                    if (target == "Left Zone"){
                        currentState = State.LEFT_TRAJ;
                        drive.followTrajectoryAsync(leftTraj);
                    } else if (target == "Center"){
                        currentState = State.CENTER_TRAJ;
                        drive.followTrajectoryAsync(centerTraj);
                    } else if (target == "Right Zone"){
                        currentState = State.RIGHT_TRAJ;
                        drive.followTrajectoryAsync(rightTraj);
                    } else {
                        //If it can't figure out a zone, do something random! Panic the drive team! Mwahahahaha!
                        double i = Math.random();
                        if (i <= 0.33){
                            currentState = State.LEFT_TRAJ;

                            drive.followTrajectoryAsync(leftTraj);
                        } else if (i >= 0.67){
                            currentState = State.CENTER_TRAJ;
                            drive.followTrajectoryAsync(centerTraj);
                        } else {
                            currentState = State.RIGHT_TRAJ;
                            drive.followTrajectoryAsync(rightTraj);
                        }
                    }
                }
                break;
            case LEFT_TRAJ:
                // Check if the drive class is busy following the trajectory
                // If not, end
                telemetry.addLine("Going to the left!");
                if (!drive.isBusy()) {
                    currentState = State.IDLE;
                }
                break;
            case CENTER_TRAJ:
                // Check if the drive class is busy following the trajectory
                // If not, end
                telemetry.addLine("Going to the center!");
                if (!drive.isBusy()) {
                    currentState = State.IDLE;
                }
                break;
            case RIGHT_TRAJ:
                // Check if the drive class is busy following the trajectory
                // If not, end
                telemetry.addLine("Going to the right!");
                if (!drive.isBusy()) {
                    currentState = State.IDLE;
                }
                break;
            case IDLE:
                // Do nothing in IDLE
                // currentState does not change once in IDLE
                // This concludes the autonomous program
                break;
        }

        // Anything outside of the switch statement will run independent of the currentState

        // We update drive continuously in the background, regardless of state
        drive.update();
        // We update our lift PID continuously in the background, regardless of state
        armPID();

        // Read pose
        Pose2d poseEstimate = drive.getPoseEstimate();

        // Continually write pose to `PoseStorage`
        PoseStorage.currentPose = poseEstimate;

        // Print pose to telemetry
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }
}
}
