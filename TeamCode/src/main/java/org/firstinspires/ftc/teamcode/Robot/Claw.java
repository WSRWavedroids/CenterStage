package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    private static final double RIGHT_CLAW_OPEN_POSITION = 0.5;
    private static final double RIGHT_CLAW_CLOSED_POSITION = 0.58;
    private static final double LEFT_CLAW_OPEN_POSITION = 0.5;
    private static final double LEFT_CLAW_CLOSED_POSITION = 0.38;

    public final Servo rightClaw;
    public final Servo leftClaw;
    public final Servo secondClaw;
    public HardwareMap hardwareMap;
    public final Telemetry telemetry;

    public Robot robot = null;

    public Claw(Servo rightClaw, Servo leftClaw, Servo secondClaw, Telemetry telemetry) {
        this.rightClaw = rightClaw;
        this.leftClaw = leftClaw;
        this.secondClaw = secondClaw;
        this.telemetry = telemetry;
    }

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
    }

    public void openClaw() {
        openLeftClaw();
        openRightClaw();
    }

    public void closeClaw() {
        closeLeftClaw();
        closeRightClaw();
    }

    // I would only keep these if the expectation is that you are closing left and right claws independently.
    public void closeLeftClaw() {
        leftClaw.setPosition(LEFT_CLAW_CLOSED_POSITION);
        telemetry.addData("Left Claw", "Closed");
    }

    public void closeRightClaw() {
        rightClaw.setPosition(RIGHT_CLAW_CLOSED_POSITION);
        telemetry.addData("Right Claw", "Closed");
    }

    public void openLeftClaw() {
        leftClaw.setPosition(LEFT_CLAW_OPEN_POSITION);
        telemetry.addData("Left Claw", "Open");
    }

    public void openRightClaw() {
        rightClaw.setPosition(RIGHT_CLAW_OPEN_POSITION);
        telemetry.addData("Right Claw", "Open");
    }

    public void closeSecondaryClaw()
    {
        secondClaw.setPosition(0.55);
    }

    public void openSecondaryClaw()
    {
        secondClaw.setPosition(.62);
    }

}
