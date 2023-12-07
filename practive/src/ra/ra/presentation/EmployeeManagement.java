package ra.ra.presentation;

import ra.business.IEmployee;
import ra.businessImp.Employee;

import java.util.*;

public class EmployeeManagement {

    public static Employee[] arrEmployees = new Employee[100];

    public static Employee employee = new Employee();
    public static int index = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n********************MENU*********************\n" +
                    "1. Nhập thông tin cho n nhân viên\n" +
                    "2. Hiển thị thông tin nhân viên\n" +
                    "3. Tính lương cho các nhân viên\n" +
                    "4. Tìm kiếm nhân viên theo tên nhân viên\n" +
                    "5. Cập nhật thông tin nhân viên\n" +
                    "6. Xóa nhân viên theo mã nhân viên\n" +
                    "7. Sắp xếp nhân viên theo lương tăng dần (Comparable)\n" +
                    "8. Sắp xếp nhân viên theo tên nhân viên giảm dần (Comparator)\n" +
                    "9. Sắp xếp nhân vên theo năm sinh tăng dần (Comparator)\n" +
                    "10. Thoát");
            System.out.print("Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    employee.inputInforEmployee(scanner);
                    break;
                case 2:
                    employee.displayInforEmployee();
                    break;
                case 3:
                    employee.calSalaryEmployee();
                    break;
                case 4:
                    employee.lookForEmployeeByName(scanner);
                    break;
                case 5:
                    employee.updateInforEmployee(scanner);
                    break;
                case 6:
                    employee.deleteEmployee(scanner);
                    break;
                case 7:
                    Employee[] newArrEmployee = new Employee[index];
                    System.arraycopy(arrEmployees, 0, newArrEmployee, 0, index);

                    Arrays.sort(newArrEmployee);
                    System.out.println("Sắp xếp nhân viên theo lương tăng dần:");
                    for (int i = 0; i < index; i++) {
                        System.out.println(newArrEmployee[i].toString());
                    }
                    break;
                case 8:
                    Employee[] newArrEmployee1 = new Employee[index];
                    System.arraycopy(arrEmployees, 0, newArrEmployee1, 0, index);

                    Arrays.sort(newArrEmployee1, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee o1, Employee o2) {
                            return -o1.getName().compareTo(o2.getName());
                        }
                    });
                    System.out.println("Sắp xếp nhân viên theo tên nhân viên giảm dần:");
                    for (int i = 0; i < index; i++) {
                        System.out.println(newArrEmployee1[i].toString());
                    }
                    break;
                case 9:
                    Employee[] newArrEmployee2 = new Employee[index];
                    System.arraycopy(arrEmployees, 0, newArrEmployee2, 0, index);

                    Arrays.sort(newArrEmployee2, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee o1, Employee o2) {
                            return o1.getYear() - o2.getYear();
                        }
                    });
                    System.out.println(" Sắp xếp nhân vên theo năm sinh tăng dần:");
                    for (int i = 0; i < index; i++) {
                        System.out.println(newArrEmployee2[i].toString());
                    }
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.print("Mời nhập từ 1 đến 10!");
                    break;
            }
        } while (true);
    }
}
