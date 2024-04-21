package iostream;
import java.io.File;
import java.util.Scanner;

public class FileSizeCalculator {
    public static void main(String[] args) {
        // Nhập đường dẫn từ người dùng
        String filePath = ""; // Đường dẫn file sẽ được lưu ở đây
        if (args.length > 0) {
            // Nếu có đối số dòng lệnh, sử dụng đối số đầu tiên làm đường dẫn
            filePath = args[0];
        } else {
            // Nếu không, yêu cầu người dùng nhập từ bàn phím
            System.out.print("Nhập đường dẫn của file: ");
            Scanner scanner = new Scanner(System.in);
            filePath = scanner.nextLine();
            scanner.close();
        }

        // Tạo đối tượng File từ đường dẫn được cung cấp
        File file = new File(filePath);

        // Kiểm tra sự tồn tại của tệp
        if (file.exists()) {
            // Lấy kích thước của tệp (đơn vị: byte)
            long fileSizeInBytes = file.length();

            // Chuyển đổi kích thước từ byte sang kilobyte
            double fileSizeInKB = fileSizeInBytes / 1024.0;

            // In kích thước của tệp ra màn hình
            System.out.println("Kích thước của tệp là: " + fileSizeInKB + " KB");
        } else {
            // Nếu tệp không tồn tại, thông báo lỗi
            System.out.println("Tệp không tồn tại hoặc đường dẫn không đúng!");
        }
    }
}
