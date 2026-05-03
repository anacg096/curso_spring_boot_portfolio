package com.portfolio.my_portfolio_backend.service;

import java.util.List;

import com.portfolio.my_portfolio_backend.model.Project;

public interface IProjectService {
    List<Project> findAll();
    Project save(Project project);
}
