package org.firstinspires.ftc.teamcode.Autonomous.RR;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TF.TensorFlow;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;

/**
 * This opmode explains how you follow multiple trajectories in succession, asynchronously. This
 * allows you to run your own logic beside the drive.update() command. This enables one to run
 * their own loops in the background such as a PID controller for a lift. We can also continuously
 * write our pose to PoseStorage.
 * <p>
 * The use of a State enum and a currentState field constitutes a "finite state machine."
 * You should understand the basics of what a state machine is prior to reading this opmode. A good
 * explanation can be found here:
 * https://www.youtube.com/watch?v=Pu7PMN5NGkQ (A finite state machine introduction tailored to FTC)
 * or here:
 * https://gm0.org/en/stable/docs/software/finite-state-machines.html (gm0's article on FSM's)
 * <p>
 * You can expand upon the FSM concept and take advantage of command based programming, subsystems,
 * state charts (for cyclical and strongly enforced states), etc. There is still a lot to do
 * to supercharge your code. This can be much cleaner by abstracting many of these things. This
 * opmode only serves as an initial starting point.
 */
@Autonomous(group = "A RoadRunner")
public class BluePlaceCloseSideRRandTF extends AutonomousPLUS {

    // This enum defines our "state"
    // This is essentially just defines the possible steps our program will take
    enum State {
        BASE_TRAJ,      // First, follow the basic instructions
        LEFT_TRAJ,      // If left, then do this
        CENTER_TRAJ,    // If center, then do this
        RIGHT_TRAJ,     // If right, then do this
        IDLE            // Our bot will enter the IDLE state when done
    }

    // We define the current state we're on
    // Default to IDLE
    State currentState = State.IDLE;

    // Define our start pose
    // This assumes we start at x: 15, y: 10, heading: 180 degrees
    Pose2d startPose = new Pose2d(10, 64, Math.toRadians(-90));

    public TensorFlow TF = new TensorFlow();

    @Override
    public void runOpMode() throws InterruptedException{

        super.runOpMode();

        // Initialize SampleMecanumDrive
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Set initial pose
        drive.setPoseEstimate(startPose);

        // Base trajectory: use no matter what prop position
        TrajectorySequence baseTraj = drive.trajectorySequenceBuilder(startPose)
                //Raise the arm
                .addDisplacementMarker(() -> {
                    robot.rotateArmUp();
                })
                .forward(0.01)
                //.strafeLeft(40)
                .build();

        TrajectorySequence leftTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                .strafeRight(27) //Check this
                .forward(16) //Check this
                .addDisplacementMarker(() -> {
                    robot.openClaw();
                })
                .back(8)
                .strafeLeft(-25)
                .build();

        TrajectorySequence centerTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                //.lineTo(new Vector2d(38,0))
                .strafeRight(38)
                .forward(24)
                .addDisplacementMarker(() -> {
                    robot.openClaw();
                })
                .back(18)
                .strafeLeft(35)
                .strafeRight(3)
                .build();

        TrajectorySequence rightTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                //.strafeRight(36)
                //.forward(10)
                //.lineToLinearHeading(new Pose2d(14,32, Math.toRadians(180)))
                //.forward(3)
                //.addDisplacementMarker(() -> {
                  //  robot.openClaw();
                //})
                //.back(36)
                //.strafeRight(10)
                //.build();

                //.strafeLeft(3)
                //.forward(28) //(-21.5,-64)
                //.turn(Math.toRadians(-90))
                //.forward(12) //(-33, -64)

                .turn(Math.toRadians(90))
                //.splineTo(new Vector2d(-3,20), Math.toRadians(-180 - 0.0000000000000000000001))
                //.strafeLeft(3)
                //.forward(28) //(-21.5,-64)
                //.turn(Math.toRadians(-90))
                .forward(12) //(-33, -64)

                /*
                .addDisplacementMarker(() -> {
                    robot.openSecondaryClaw();
                })
                .back(40)
                .turn(Math.toRadians(180))
                .back(7)
                .strafeLeft(8)
                //.lineToLinearHeading(new Pose2d(43,-36, Math.toRadians(0)))
                .addDisplacementMarker(() -> {
                    robot.rotateArmUp();
                    robot.openClaw();
                })
                .back(3)
                 */

                .build();


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

        waitForStart();



        // Set the current state to TRAJECTORY_1, our first step
        // Then have it follow that trajectory
        // Make sure you use the async version of the commands
        // Otherwise it will be blocking and pause the program here until the trajectory finishes
        currentState = State.BASE_TRAJ;
        drive.followTrajectorySequenceAsync(baseTraj);

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

                        /*
                        if (target == "Left Zone"){
                            currentState = State.LEFT_TRAJ;
                            drive.followTrajectorySequenceAsync(leftTraj);
                        } else if (target == "Center"){
                            currentState = State.CENTER_TRAJ;
                            drive.followTrajectorySequenceAsync(centerTraj);
                        } else if (target == "Right Zone"){
                            currentState = State.RIGHT_TRAJ;
                            drive.followTrajectorySequenceAsync(rightTraj);
                        } else {
                            //If it can't figure out a zone, do something random! Panic the drive team! Mwahahahaha!
                            double i = Math.random();
                            if (i <= 0.33){
                                currentState = State.LEFT_TRAJ;
                                drive.followTrajectorySequenceAsync(leftTraj);
                            } else if (i >= 0.67){
                                currentState = State.CENTER_TRAJ;
                                drive.followTrajectorySequenceAsync(centerTraj);
                            } else {
                                currentState = State.RIGHT_TRAJ;
                                drive.followTrajectorySequenceAsync(rightTraj);
                            }
                        }

                         */
                        currentState = State.RIGHT_TRAJ;
                        drive.followTrajectorySequenceAsync(rightTraj);

                    }
                    break;
                case LEFT_TRAJ:
                    // Check if the drive class is busy following the trajectory
                    // If not, end
                    if (!drive.isBusy()) {
                        currentState = State.IDLE;
                    }
                    break;
                case CENTER_TRAJ:
                    // Check if the drive class is busy following the trajectory
                    // If not, end
                    if (!drive.isBusy()) {
                        currentState = State.IDLE;
                    }
                    break;
                case RIGHT_TRAJ:
                    // Check if the drive class is busy following the trajectory
                    // If not, end
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
            //armPID();

            // Read pose
            Pose2d poseEstimate = drive.getPoseEstimate();

            // Continually write pose to `PoseStorage`
            PoseStorage.currentPose = poseEstimate;

            // Print pose to telemetry
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();

            if (isStopRequested()) break;
        }
    }

    // Assume we have a hardware class called lift
    // Lift uses a PID controller to maintain its height
    // Thus, update() must be called in a loop
    class Lift {
        public Lift(HardwareMap hardwareMap) {
            // Beep boop this is the the constructor for the lift
            // Assume this sets up the lift hardware
        }

        public void update() {
            //armPID();
        }
    }
}