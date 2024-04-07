package week11.arch;
import java.util.*;

public class Main {

    static class Block implements Comparable<Block> {
        String type;
        int size;

        public Block(String type, int size) {
            this.type = type;
            this.size = size;
        }

        public double contactSurface() {
            if ("cube".equals(type)) {
                return size * size;
            } else {
                return Math.PI * size * size;
            }
        }

        @Override
        public int compareTo(Block other) {
            return Double.compare(this.contactSurface(), other.contactSurface());
        }

        @Override
        public String toString() {
            return type + " " + size;
        }
    }

    public static void main(String[] args) {
        Scanner scanner
                = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();


        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            blocks.add(new Block(parts[0], Integer.parseInt(parts[1])));
        }

        Collections.sort(blocks);

        if (isStackPossible(blocks)) {
            for (Block block : blocks) {
                System.out.println(block);
            }
        } else {
            System.out.println("impossible");
        }
    }

    private static boolean isStackPossible(List<Block> blocks) {
        for (int i = 0; i < blocks.size() - 1; i++) {
            Block top = blocks.get(i);
            Block bottom = blocks.get(i + 1);

            if (!isPlacementValid(top, bottom)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPlacementValid(Block top, Block bottom) {
        if (top.type.equals("cylinder") && bottom.type.equals("cube")) {
            return top.size * 2 <= bottom.size;
        } else if (top.type.equals("cube") && bottom.type.equals("cylinder")) {
            double diagonal = Math.sqrt(2) * top.size;
            return diagonal <= bottom.size * 2;
        }
        return true; // Same type or cube on cube
    }
}

