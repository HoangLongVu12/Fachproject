package week11.arch;

import java.util.*;

public class arch {

    static class Block implements Comparable<Block> {
        String type;
        int size;
        double effectiveDiameter;

        Block(String type, int size) {
            this.type = type;
            this.size = size;
            this.effectiveDiameter = (type.equals("cube")) ? size * Math.sqrt(2) : 2 * size;
        }

        @Override
        public int compareTo(Block other) {
            return Double.compare(other.effectiveDiameter, this.effectiveDiameter);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int size = scanner.nextInt();
            blocks.add(new Block(type, size));
        }

        Collections.sort(blocks);

        if (isTowerPossible(blocks)) {
            for (Block block : blocks) {
                System.out.println(block.type + " " + block.size);
            }
        } else {
            System.out.println("impossible");
        }
    }

    private static boolean isTowerPossible(List<Block> blocks) {
        for (int i = 0; i < blocks.size() - 1; i++) {
            if (blocks.get(i + 1).effectiveDiameter > blocks.get(i).effectiveDiameter) {
                return false;
            }
        }
        return true;
    }
}