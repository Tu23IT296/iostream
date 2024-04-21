package iostream;
import java.io.File;
import java.util.Scanner;

public class FileDeleter {
    public static void main(String[] args) {
        // Nhập đường dẫn từ người dùng
        String pathToDelete = ""; // Đường dẫn thư mục hoặc tệp sẽ được xoá
        if (args.length > 0) {
            // Nếu có đối số dòng lệnh, sử dụng đối số đầu tiên làm đường dẫn
            pathToDelete = args[0];
        } else {
            // Nếu không, yêu cầu người dùng nhập từ bàn phím
            System.out.print("Nhập đường dẫn của thư mục hoặc tệp cần xoá: ");
            Scanner scanner = new Scanner(System.in);
            pathToDelete = scanner.nextLine();
            scanner.close();
        }

        // Tạo đối tượng File từ đường dẫn được cung cấp
        File fileOrDirectoryToDelete = new File(pathToDelete);

        // Kiểm tra sự tồn tại của tệp hoặc thư mục
        if (!fileOrDirectoryToDelete.exists()) {
            // Nếu không tồn tại, thông báo lỗi và kết thúc chương trình
            System.out.println("Thư mục hoặc tệp không tồn tại!");
            return;
        }

        // Kiểm tra xem đối tượng đó là tệp hay thư mục
        if (fileOrDirectoryToDelete.isFile()) {
            // Nếu là tệp, yêu cầu xác nhận trước khi xoá
            System.out.print("Bạn có chắc chắn muốn xoá tệp này? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine().toLowerCase();
            scanner.close();

            if (confirmation.equals("yes")) {
                // Nếu người dùng xác nhận muốn xoá, thực hiện xoá
                if (fileOrDirectoryToDelete.delete()) {
                    System.out.println("Tệp đã được xoá thành công!");
                } else {
                    System.out.println("Đã có lỗi xảy ra khi xoá tệp!");
                }
            } else {
                // Nếu người dùng không xác nhận, thoát khỏi chương trình
                System.out.println("Không có thay đổi nào được thực hiện.");
            }
        } else if (fileOrDirectoryToDelete.isDirectory()) {
            // Nếu là thư mục, yêu cầu xác nhận trước khi xoá
            System.out.print("Bạn có chắc chắn muốn xoá thư mục này và tất cả các tệp con bên trong? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine().toLowerCase();
            scanner.close();

            if (confirmation.equals("yes")) {
                // Nếu người dùng xác nhận muốn xoá, thực hiện xoá
                deleteDirectory(fileOrDirectoryToDelete);
                System.out.println("Thư mục đã được xoá thành công!");
            } else {
                // Nếu người dùng không xác nhận, thoát khỏi chương trình
                System.out.println("Không có thay đổi nào được thực hiện.");
            }
        }
    }

    // Phương thức đệ quy để xoá thư mục và tất cả các tệp con bên trong
    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }
}
