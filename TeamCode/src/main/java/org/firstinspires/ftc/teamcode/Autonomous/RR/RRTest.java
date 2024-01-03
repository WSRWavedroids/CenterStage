package org.firstinspires.ftc.teamcode.Autonomous.RR;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(group = "RR", name = "RR Test")
public class RRTest extends OpMode {

    public SampleMecanumDrive drive;
    //Robot robot = new Robot(hardwareMap, telemetry,this);
    //AutonomousPLUS AP = new AutonomousPLUS();

    @Override
    public void init() {
        drive = new SampleMecanumDrive(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("Status", "Initialized");
        //Basic procedure for setting up AprilTags recognition
        //org.firstinspires.ftc.teamcode.Autonomous.AprilTags.MayFlowers MayFlowers = new MayFlowers();
    }

    @Override
    public void start() {

        //Control hub is offset 4.5 inches in x and 3 inches in y

        //Setting starting position
        Pose2d startPose = new Pose2d(10, 64, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

                TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                        //Move forward 150 ticks/6 inches (just enough to not be scraping the wall)
                        .forward(6)

                        //Move left 1800 ticks/40 inches? (into the backstage to push yellow pixel)
                        .strafeLeft(6)

                        //Move right 1650 ticks/36 inches? (back to center spike mark)
                        .strafeRight(6)

                        //Move forward 1080 ticks/18 inches? (up to center spike mark)
                        .back(6)

                        .build();
                drive.followTrajectorySequenceAsync(trajSeq);
            }


            @Override
            public void loop() {

                drive.update();

                //AP.armPID();

            }

        }