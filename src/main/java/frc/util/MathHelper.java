package frc.util;

/**
 * Add your docs here.
 */
public class MathHelper {

    public static double map(double x, double in_min, double in_max, double out_min, double out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}
