package ra.businessImp;

import ra.business.IEmployee;
import ra.ra.presentation.EmployeeManagement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Employee implements IEmployee, Comparable<Employee> {
    private String Id;
    private String Name;
    private int Year;
    private float Rate;
    private float Commission;
    private float Salary;
    private Boolean Status;

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã nhân viên:");
        this.Id = scanner.nextLine();
        System.out.print("Nhập tên nhân viên:");
        this.Name = scanner.nextLine();
        System.out.print("Nhập năm sinh nhân viên:");
        this.Year = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập hệ số lương nhân viên:");
        this.Rate = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập hoa hồng của nhân viên hàng tháng:");
        this.Commission = Float.parseFloat(scanner.nextLine());
        do {
            System.out.print("Nhập trạng thái nhân viên:");
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                this.Status = Boolean.parseBoolean(status);
                break;
            } else {
                System.out.println("Phải nhập giá trị true hoặc false");
            }
        } while (true);
    }

    public void updateData(Scanner scanner) {
        System.out.print("Nhập tên nhân viên:");
        String name = scanner.nextLine();
        if (!this.Name.isEmpty()) {
            this.Name = name;
        }
        System.out.print("Nhập năm sinh nhân viên:");
        String year = scanner.nextLine();
        if (!year.isEmpty()) {
            this.Year = Integer.parseInt(year);
        }
        System.out.print("Nhập hệ số lương nhân viên:");
        String rate = scanner.nextLine();
        if (!rate.isEmpty()) {
            this.Rate = Float.parseFloat(rate);
        }
        System.out.print("Nhập hoa hồng của nhân viên hàng tháng:");
        String commission = scanner.nextLine();
        if (commission.isEmpty()) {
            this.Commission = Float.parseFloat(commission);
            this.calSalary();
        }
        System.out.print("Nhập trạng thái nhân viên:");
        String status = scanner.nextLine();
        if (!status.isEmpty()) {
            do {
                if (status.equals("true") || status.equals("false")) {
                    this.Status = Boolean.parseBoolean(status);
                    break;
                } else {
                    System.out.println("Phải nhập giá trị true hoặc false");
                }
            } while (true);
        }
    }

    @Override
    public void displayData() {
        System.out.printf("Mã nhân viên: %s - Tên nhân viên: %s - Năm sinh: %d - Hệ số lương nhân viên: %.0f\n" +
                        "Hòa hồng của nhân viên hàng tháng: %.0f - Lương nhân viên hàng tháng: %.0f - Trạng thái nhân viên: %s\n"
                , this.Id, this.Name, this.Year, this.Rate, this.Commission, this.Salary, this.Status ? "Đang làm việc" : "Nghỉ việc");
    }

    public void calSalary() {
        this.Salary = this.Rate * BASIC_SALARY + this.Commission;
    }

    public Employee() {
    }

    public Employee(String id, String name, int year, float rate, float commission, float salary, Boolean status) {
        Id = id;
        Name = name;
        Year = year;
        Rate = rate;
        Commission = commission;
        Salary = salary;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public float getCommission() {
        return Commission;
    }

    public void setCommission(float commission) {
        Commission = commission;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public void inputInforEmployee(Scanner scanner) {
        int index = EmployeeManagement.index;
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        System.out.print("Nhập số nhân viên để nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhập thông tin nhân viên:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Nhân viên thứ %d:\n", i + 1);
            arrEmployees[index] = new Employee();
            arrEmployees[index].inputData(scanner);
            index++;
        }
        EmployeeManagement.index = index;
    }

    public void displayInforEmployee() {
        int index = EmployeeManagement.index;
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        for (int i = 0; i < index; i++) {
            arrEmployees[i].displayData();
        }
    }

    public void calSalaryEmployee() {
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        int index = EmployeeManagement.index;
        for (int i = 0; i < index; i++) {
            arrEmployees[i].calSalary();
        }
        System.out.println("Tính thành công! Ấn 2 để kiểm tra");
    }

    public void lookForEmployeeByName(Scanner scanner) {
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        int index = EmployeeManagement.index;
        System.out.print("Nhập tên nhân viên muốn tìm kiếm:");
        String name = scanner.nextLine();
        boolean isCheck = false;
        for (int i = 0; i < index; i++) {
            if (arrEmployees[i].getName().contains(name)) {
                arrEmployees[i].displayData();
                isCheck = true;
            }
        }
        if (!isCheck) {
            System.out.print("Không tìm thấy nhân viên!");
        }
    }

    public void updateInforEmployee(Scanner scanner) {
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        do {
            System.out.print("Nhập mã nhân viên cần cập nhật:");
            String id = scanner.nextLine();
            int indexUpdate = lookForIndexEmployee(id);
            if (indexUpdate < 0) {
                System.out.println("Mã không tồn tại! Mời nhập lại");
            } else {
                arrEmployees[indexUpdate].updateData(scanner);
                System.out.println("Cập nhật thành công! Ấn 2 để kiểm tra");
                break;
            }
        } while (true);
    }

    public void deleteEmployee(Scanner scanner) {
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        int index = EmployeeManagement.index;
        do {
            System.out.print("Nhập mã nhân viên cần xóa:");
            String id = scanner.nextLine();
            int indexDelete = lookForIndexEmployee(id);
            if (indexDelete < 0) {
                System.out.println("Mã không tồn tại! Mời nhập lại");
            } else {
                for (int i = indexDelete; i < index; i++) {
                    arrEmployees[i] = arrEmployees[i + 1];
                }
                arrEmployees[index - 1] = null;
                index--;
                EmployeeManagement.index = index;
                System.out.println("Xóa thành công! Ấn 2 để kiểm tra");
                break;
            }
        } while (true);
    }

    public int lookForIndexEmployee(String id) {
        Employee[] arrEmployees = EmployeeManagement.arrEmployees;
        int index = EmployeeManagement.index;
        for (int i = 0; i < index; i++) {
            if (arrEmployees[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public int compareTo(Employee employee) {

        return Double.compare(this.getSalary(), employee.getSalary());
    }

    public String toString() {
        return "Mã nhân viên:" + this.Id + " - Tên nhân viên:" + this.Name + " - Năm sinh nhân viên:" + this.Year +
                "\nHệ số lương nhân viên:" + this.Rate + " - Hoa hồng của nhân viên hàng tháng :" + this.Commission +
                " - Lương nhân viên hàng tháng:" + this.Salary + " Trạng thái nhân viên:" + this.Status;
    }

}
