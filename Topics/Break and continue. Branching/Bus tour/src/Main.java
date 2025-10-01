import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lengthBus = scanner.nextInt();
        int totalBridge = scanner.nextInt();
        int lengthBridge = 0;
        int bridge = 0;
        boolean isCrash = false;

        for (int i = 0; i < totalBridge; i++) {
            bridge++;
            lengthBridge = scanner.nextInt();
            if(lengthBridge <= lengthBus) {
                isCrash = true;
                break;
            }
        }

        System.out.println(isCrash ? "Will crash on bridge " + bridge
                : "Will not crash");
    }
}