package week11.sweep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] lineSplit = line.split(" ");
        int housesCount = Integer.parseInt(lineSplit[0]);
        int flyLinesCount = Integer.parseInt(lineSplit[1]);
        ArrayList<Float>[] housesX = new ArrayList[housesCount];
        ArrayList<Float>[] housesY = new ArrayList[housesCount];
        Float[][] flyLines = new Float[flyLinesCount][6];
        for (int i = 0; i < housesCount; i++) {
            housesX[i] = new ArrayList<>();
            housesY[i] = new ArrayList<>();
            int cornersCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < cornersCount; j++) {
                lineSplit = br.readLine().split(" ");
                housesX[i].add(Float.parseFloat(lineSplit[0]));
                housesY[i].add(Float.parseFloat(lineSplit[1]));
            }
        }
        for (int i = 0; i < flyLinesCount; i++) {
            lineSplit = br.readLine().split(" ");
            // start point
            flyLines[i][0] = Float.parseFloat(lineSplit[0]);
            flyLines[i][1] = Float.parseFloat(lineSplit[1]);
            flyLines[i][2] = Float.parseFloat(lineSplit[2]);
            // end point
            flyLines[i][3] = Float.parseFloat(lineSplit[3]);
            flyLines[i][4] = Float.parseFloat(lineSplit[4]);
            flyLines[i][5] = Float.parseFloat(lineSplit[5]);
        }

        // houses that a line can see
        ArrayList<Integer>[] scannableHouses = new ArrayList[flyLinesCount];
        for (int i = 0; i < flyLinesCount; i++) {
            scannableHouses[i] = new ArrayList<>();
            for (int j = 0; j < housesCount; j++) {
                scannableHouses[i].add(j);
            }
        }

        // visibility from fly lines to houses
        for (int i = 0; i < flyLinesCount; i++) {
            Float[] flyLineVec1 = vector(flyLines[i][0], flyLines[i][1], flyLines[i][3], flyLines[i][4]);
            Float[] flyLineVec2 = new Float[]{-flyLineVec1[0], -flyLineVec1[1]};
            for (int j = 0; j < housesCount; j++) {
                if (!scannableHouses[i].contains(j)) {
                    break;
                }
                for (int k = 0; k < housesX[j].size(); k++) {
                    Float[] cornerToFlyLineVec = vector(flyLines[i][0], flyLines[i][1], housesX[j].get(k), housesY[j].get(k));
                    if (vectorCos(flyLineVec1, cornerToFlyLineVec) < 0) {
                        scannableHouses[i].remove((Integer) j);
                        break;
                    }
                    cornerToFlyLineVec = vector(flyLines[i][3], flyLines[i][4], housesX[j].get(k), housesY[j].get(k));
                    if (vectorCos(flyLineVec2, cornerToFlyLineVec) < 0) {
                        scannableHouses[i].remove((Integer) j);
                        break;
                    }
                }
            }
        }
        Integer result = isPossible(scannableHouses, housesX, housesY, flyLines);
        if (result == -1) {
            System.out.println("impossible");
        } else {
            System.out.println(result);
        }
    }

    private static Integer isPossible(ArrayList<Integer>[] scannableHouses,
                                      ArrayList<Float>[] housesX, ArrayList<Float>[] housesY, Float[][] flyLines) {
        int housesCount = housesX.length;
        int flyLinesCount = flyLines.length;

        // check if each house is scannable by at least 1 line
        for (int i = 0; i < housesCount; i++) {
            for (int j = 0; j < flyLinesCount; j++) {
                if (scannableHouses[j].contains(i)) {
                    break;
                }
                if (j == flyLinesCount - 1) {
                    return -1;
                }
            }
        }

        Integer[] minAngleOfHouses = new Integer[housesCount];
        for (int i = 0; i < housesCount; i++) {
            minAngleOfHouses[i] = 360;
        }
        Integer maxAngleOfLines = 0;
        for (int i = 0; i < housesCount; i++) {
            for (int k = 0; k < flyLinesCount; k++) {
                if (scannableHouses[k].contains(i)) {
                    Integer houseToLineAngle = 0;
                    Integer fullCornerAngle;
                    // check corners
                    for (int j = 0; j < housesX[i].size(); j++) {
                        // angle from 1 corner to fly line
                        fullCornerAngle = cornerToLineAngle(housesX[i], housesY[i], j, flyLines[k]);
                        houseToLineAngle = Math.max(fullCornerAngle, houseToLineAngle);
                    }
                    minAngleOfHouses[i] = Math.min(minAngleOfHouses[i], houseToLineAngle);
                }
            }
            maxAngleOfLines = Math.max(maxAngleOfLines, minAngleOfHouses[i]);
        }
        return maxAngleOfLines;
    }

    private static Integer cornerToLineAngle(ArrayList<Float> housesX, ArrayList<Float> doubles, int cornerNr, Float[] flyLine) {
        Integer fullCornerAngle;
        Float[] flyLineVec;
        Float[] cornerToLineVec;
        if (flyLine[5] >= flyLine[2]) {
            // MP
            flyLineVec = vector(flyLine[0], flyLine[1], flyLine[3], flyLine[4]);
            // MQ
            cornerToLineVec = vector(flyLine[0], flyLine[1], housesX.get(cornerNr), doubles.get(cornerNr));
        } else {
            // MP
            flyLineVec = vector(flyLine[3], flyLine[4], flyLine[0], flyLine[1]);
            // MQ
            cornerToLineVec = vector(flyLine[3], flyLine[4], housesX.get(cornerNr), doubles.get(cornerNr));
        }
        // cos(MP, MQ)
        Float cos = vectorCos(flyLineVec, cornerToLineVec);
        // MQ'
        Float adjaEdgeLength = cos * vectorLength(cornerToLineVec);
        // MQ'/MP
        Float ratio = adjaEdgeLength / vectorLength(flyLineVec);
        // PL
        Float heightDiff = Math.abs(flyLine[5] - flyLine[2]);
        // Q'Q''
        Float tempHeight = ratio * heightDiff;
        // A'Q'' = Q'Q'' + QA
        Float fullHeight = tempHeight + Math.min(flyLine[5], flyLine[2]);
        // root(QQ') = MQ^2 - MQ'^2
        Float oppoEdgeLength = Float.parseFloat(Math.sqrt(vectorLength(cornerToLineVec) * vectorLength(cornerToLineVec)
                - adjaEdgeLength * adjaEdgeLength) + "");
        // tan = AA' / A'Q''
        Float tan = oppoEdgeLength / fullHeight;
        // angle = AQ''A'
        fullCornerAngle = (int) Math.ceil(Math.toDegrees(Math.atan(tan)));
        return fullCornerAngle;
    }

    private static Float[] vector(Float startPointX, Float startPointY, Float endPointX, Float endPointY) {
        return new Float[]{endPointX - startPointX, endPointY - startPointY};
    }

    private static Float vectorCos(Float[] vector1, Float[] vector2) {
        return vectorMult(vector1, vector2) / (vectorLength(vector1) * vectorLength(vector2));
    }

    private static Float vectorLength(Float[] vector) {
        return Float.parseFloat(Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]) + "");
    }

    private static Float vectorMult(Float[] vector1, Float[] vector2) {
        return vector1[0] * vector2[0] + vector1[1] * vector2[1];
    }
}
