package br.com.deveficiente.bolaoapi.shared.validator;

import br.com.deveficiente.bolaoapi.services.team.TeamRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class DatabaseTeamsValidator implements ConstraintValidator<DatabaseTeams, Set<Long>> {

    private final TeamRepository teamRepository;

    public DatabaseTeamsValidator(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean isValid(Set<Long> ids, ConstraintValidatorContext context) {
        long requestCount = ids.size();
        long databaseCount = teamRepository.findAllById(ids)
                .stream()
                .count();

        return requestCount == databaseCount;
    }
}
