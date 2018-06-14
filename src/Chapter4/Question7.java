package Chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
 * projects, where the second project is dependent on the first project). All of a project's dependencies
 * must be built before the project is. Find a build order that will allow the projects to be built. If there
 * is no valid build order, return an error.
 * EXAMPLE
 * Input:
 * projects: a, b, c, d, e, f
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 */
class Question7 {

    private static class Project {

        private List<Project> dependentProjects;
        private String name;

        public Project(String name) {
            this.name = name;
            this.dependentProjects = new ArrayList<>();
        }

        public void addDependent(Project project) {
            this.dependentProjects.add(project);
        }

        public List<Project> getDependentProjects() {
            return dependentProjects;
        }

        public String getName() {
            return name;
        }
    }

    private static class ProjectDependency {

        private Project firstProject;
        private Project secondProject;

        public ProjectDependency(Project firstProject, Project secondProject) {
            this.firstProject = firstProject;
            this.secondProject = secondProject;
        }

        public Project getFirstProject() {
            return firstProject;
        }

        public Project getSecondProject() {
            return secondProject;
        }
    }

    public List<Project> buildOrder(List<Project> projects, List<ProjectDependency> dependencies) {
        addProjectDependencies(projects, dependencies);
        List<Project> order = new ArrayList<>();
        while (!projects.isEmpty()) {
            Project project = getNonDependentProject(projects);
            if (project == null) {
                return null;
            }
            order.add(project);
            removeProjectFromDependentProjects(project, projects);
            projects.remove(project);
        }
        return order;
    }

    private Project getNonDependentProject(List<Project> projects) {
        for (Project project : projects) {
            if (project.getDependentProjects().isEmpty()) {
                return project;
            }
        }
        return null;
    }

    private void removeProjectFromDependentProjects(Project toBeRemoved, List<Project> projects) {
        for (Project project : projects) {
            project.getDependentProjects().remove(toBeRemoved);
        }
    }

    private void addProjectDependencies(List<Project> projects, List<ProjectDependency> dependencies) {
        for (ProjectDependency projectDependency : dependencies) {
            projectDependency.getSecondProject().addDependent(projectDependency.getFirstProject());
        }
    }

    public static void main(String[] args) {
        Question7 question7 = new Question7();
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("a"));
        projects.add(new Project("b"));
        projects.add(new Project("c"));
        projects.add(new Project("d"));
        projects.add(new Project("e"));
        projects.add(new Project("f"));

        List<ProjectDependency> projectDependencies = Arrays.asList(
                new ProjectDependency(projects.get(0), projects.get(3)),
                new ProjectDependency(projects.get(5), projects.get(1)),
                new ProjectDependency(projects.get(1), projects.get(3)),
                new ProjectDependency(projects.get(5), projects.get(0)),
                new ProjectDependency(projects.get(3), projects.get(2))
        );
        List<Project> projectOrder = question7.buildOrder(projects, projectDependencies);
        for (Project project : projectOrder) {
            System.out.println(project.getName());
        }
    }

}
