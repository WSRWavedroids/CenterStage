package org.firstinspires.ftc.teamcode.Autonomous.RR;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
@Autonomous(name = "Test FATA", group = "A RoadRunner")
public class TestFATA extends AutonomousPLUS {

    // Define our start pose
    // This assumes we start at x: 15, y: 10, heading: 180 degrees
    Pose2d startPose = new Pose2d(10, 64, Math.toRadians(-90));

    public TensorFlow TF = new TensorFlow();
    public FollowAutoTrajAsync FATA = new FollowAutoTrajAsync();

    @Override
    public void runOpMode() throws InterruptedException{

        super.runOpMode();

        // Initialize SampleMecanumDrive
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Base trajectory: use no matter what prop position
        TrajectorySequence baseTraj = drive.trajectorySequenceBuilder(startPose)
                .forward(1)
                .addDisplacementMarker(() -> {
                    telemetry.addLine("Did the base traj");
                 })
                .build();

        TrajectorySequence leftTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                .forward(1)
                .addDisplacementMarker(() -> {
                    telemetry.addLine("Did the left traj");
                })
                .build();

        TrajectorySequence centerTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                .forward(1)
                .addDisplacementMarker(() -> {
                    telemetry.addLine("Did the center traj");
                })
                .build();

        TrajectorySequence rightTraj = drive.trajectorySequenceBuilder(baseTraj.end())
                .forward(1)
                .addDisplacementMarker(() -> {
                    telemetry.addLine("Did the right traj");
                })
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

        FATA.executeFATA(drive, baseTraj, leftTraj, centerTraj, rightTraj, startPose);
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
            armPID();
        }
    }
}