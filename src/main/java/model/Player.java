package model;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Player {
	
	private int id;
	private String name;
	private LocalDate birthDate;
	private double height;
	private double weight;
	private Team team;
	
}
