package github.nikhrom.javatraining.spring.mvc_hibernate.service;

import github.nikhrom.javatraining.spring.mvc_hibernate.dao.DepartmentDao;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.CreateDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component

@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;
    private final DepartmentMapper departmentMapper;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final CreateDepartmentDtoMapper createDepartmentDtoMapper;

    @Transactional
    public List<DepartmentDto> getAllDepartments(){
       return departmentDao.getAll()
               .stream()
               .map(departmentMapper::mapFrom)
               .collect(Collectors.toList());
    };

    @Transactional
    public DepartmentDto addDepartment(CreateDepartmentDto departmentDto){
        var department = createDepartmentDtoMapper.mapFrom(departmentDto);
        var savedDepartment = departmentDao.save(department);
        return departmentMapper.mapFrom(savedDepartment);
    }

    @Transactional
    public Optional<DepartmentDto> getDepartmentById(Integer id){
        return departmentDao.get(id)
                .map(departmentMapper::mapFrom);
    }

    @Transactional
    public void updateDepartment(DepartmentDto departmentDto){
        departmentDao.update(departmentDtoMapper.mapFrom(departmentDto));
    }

    @Transactional
    public void deleteDepartmentById(Integer id){
        var department = Department.builder().id(id).build();
        departmentDao.delete(department);
    }
}
