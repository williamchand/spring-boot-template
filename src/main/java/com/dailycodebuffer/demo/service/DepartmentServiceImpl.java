package com.dailycodebuffer.demo.service;

import com.dailycodebuffer.demo.controller.DepartmentController;
import com.dailycodebuffer.demo.entity.Department;
import com.dailycodebuffer.demo.error.DepartmentNotFoundException;
import com.dailycodebuffer.demo.repository.DepartmentRepository;
import com.dailycodebuffer.demo.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    final private Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department =  departmentRepository.findById(departmentId);
        if (!department.isPresent()) {
            throw new DepartmentNotFoundException("Department not available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();

        if(AppUtils.validateStringNonNull(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());
        }

        if(AppUtils.validateStringNonNull(department.getDepartmentCode())) {
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        if(AppUtils.validateStringNonNull(department.getDepartmentAddress())) {
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

}
