package org.firstinspires.ftc.teamcode.Autonomous.RR;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
@Autonomous(name = "Red Close Score RR", group = "A RoadRunner")
public class RedCloseScoreRR extends AutonomousPLUS {

    // This enum defines our "state"
    // This is essentially just defines the possible steps our program will take
    enum State {
        BASE_TRAJ,      // First, follow the basic instructions
        LEFT_TRAJ,      // If left, then do this
        CENTER_TRAJ,    // If center, then do this
        RIGHT_TRAJ,     // If right, then do this
        IDLE            // Our bot will enter the IDLE state when done
    }

    public TrajectorySequence targetTraj;

    // We define the current state we're on
    // Default to IDLE
    State currentState = State.IDLE;

    // Define our start pose
    // This assumes we start at x: 15, y: 10, heading: 180 degrees
    Pose2d startPose = new Pose2d(10, -64, Math.toRadians(90));

    public TensorFlow TF = new TensorFlow();

    @Override
    public void runOpMode() throws InterruptedException{

        super.runOpMode();

        // Initialize SampleMecanumDrive
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Set initial pose
        drive.setPoseEstimate(startPose);

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

        if (target == "Left Zone"){
            TrajectorySequence leftTraj = drive.trajectorySequenceBuilder(startPose)
                    .forward(24)
                    //Turn to truss
                    .lineToLinearHeading(new Pose2d(14,-35, Math.toRadians(180)))
                    .forward(14)
                    .addDisplacementMarker(() -> {
                        robot.openSecondaryClaw();
                        robot.rotateArmUp();
                    })
                    .back(18)
                    .splineToLinearHeading(new Pose2d(44, -28,Math.toRadians(0)), Math.toRadians(90))                                .strafeRight(3)
                    .forward(2)
                    .addDisplacementMarker(() -> {
                        robot.openClaw();
                    })
                    .strafeRight(30)
                    .forward(6)
                    .build();
            targetTraj = leftTraj;

        } else if (target == "Center"){
            TrajectorySequence centerTraj = drive.trajectorySequenceBuilder(startPose)
                    .forward(44)
                    .addDisplacementMarker(() -> {
                        robot.openSecondaryClaw();
                        robot.rotateArmUp();
                    })
                    .back(24)
                    //THESE COORDS ARE NOT RIGHT
                    .splineToLinearHeading(new Pose2d(44, -38,Math.toRadians(0)), Math.toRadians(90))
                    .addDisplacementMarker(() -> {
                        robot.openClaw();
                    })
                    .forward(2)
                    .strafeRight(18)
                    .forward(6)
                    .build();

            targetTraj = centerTraj;

        } else if (target == "Right Zone"){
            TrajectorySequence rightTraj = drive.trajectorySequenceBuilder(startPose)

                    .forward(6)
                    .strafeRight(12) //Check this
                    .forward(30) //Check this
                    .addDisplacementMarker(() -> {
                        robot.openSecondaryClaw();
                        robot.rotateArmUp();
                    })
                    .back(12)
                    .splineToLinearHeading(new Pose2d(44, -40,Math.toRadians(0)), Math.toRadians(90))
                    .addDisplacementMarker(() -> {
                        robot.openClaw();
                    })
                    .forward(2)
                    .strafeRight(18)
                    .forward(6)
                    .build();

            targetTraj = rightTraj;

        } else {
            TrajectorySequence leftTraj = drive.trajectorySequenceBuilder(startPose)
                    .forward(24)
                    //Turn to truss
                    .lineToLinearHeading(new Pose2d(14,-35, Math.toRadians(180)))
                    .forward(14)
                    .addDisplacementMarker(() -> {
                        robot.openSecondaryClaw();
                        robot.rotateArmUp();
                    })
                    .back(18)
                    .splineToLinearHeading(new Pose2d(44, -32,Math.toRadians(0)), Math.toRadians(90))                                .strafeRight(3)
                    .forward(2)
                    .addDisplacementMarker(() -> {
                        robot.openClaw();
                    })
                    .strafeRight(30)
                    .forward(6)
                    .build();

            targetTraj = leftTraj;

        }

        // Set the current state to TRAJECTORY_1, our first step
        // Then have it follow that trajectory
        // Make sure you use the async version of the commands
        // Otherwise it will be blocking and pause the program here until the trajectory finishes
        drive.followTrajectorySequenceAsync(targetTraj);

        while (opModeIsActive() && !isStopRequested()) {

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