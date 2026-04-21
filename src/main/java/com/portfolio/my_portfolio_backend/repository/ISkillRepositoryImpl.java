package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Skill;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.util.Objects;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
@RequiredArgsConstructor
public class ISkillRepositoryImpl implements ISkillRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Skill> skillRowMapper = (ResultSet rs, int rowNum) -> {
        Skill info = new Skill();

        info.setId(rs.getLong("id"));
        info.setName(rs.getString("name"));
        info.setLevelPercentage(rs.getInt("level_percentage"));
        info.setIconClass(rs.getString("icon_class"));
        info.setPersonalInfoId(rs.getLong("personal_info_id"));

        return info;
    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String sql = "INSERT INTO skill ( " +
                    "name, " +
                    "level_percentage, " +
                    "icon_class, " +
                    "personal_info_id, " +
                    ") VALUES (?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getLevelPercentage());
                ps.setString(3, skill.getIconClass());
                ps.setLong(4, skill.getPersonalInfoId());

                return ps;
            }, keyHolder);

            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
            // jdbcTemplate.update (sql,
            // skill.getName(),
            // skill.getLevelPercentage(),
            // skill.getIconClass(),
            // skill.getPersonalInfoId(),
            // );
        } else {
            String sql = "UPDATE skills SET " +
                    "name = ?, " +
                    "level_percentage = ?, " +
                    "icon_class = ?, " +
                    "personal_info_id = ?, " +
                    "WHERE id = ?";

            jdbcTemplate.update(sql,
                    skill.getName(),
                    skill.getLevelPercentage(),
                    skill.getIconClass(),
                    skill.getPersonalInfoId(),
                    skill.getId());
        }

        return skill;
    }

    // @Override
    // public Optional<Skill> findById(Long id){
    // String sql = "SELECT * FROM skills WHERE id = ?";
    // List<Skill> results = jdbcTemplate.query(sql, skillRowMapper,
    // id);

    // return results.stream().findFirst();
    // }
    @Override
    public Optional<Skill> findById(Long id) {
        String sql = "SELECT * FROM skills WHERE id = ?";
        try {
            Skill info = jdbcTemplate.queryForObject(sql, skillRowMapper, id);
            return Optional.ofNullable(info);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skills";
        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM skills WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT * FROM skills WHERE personal_info_id = ?";
        
        return jdbcTemplate.query(sql, skillRowMapper, personalInfoId);
    }

}
