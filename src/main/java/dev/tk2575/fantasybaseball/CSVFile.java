package dev.tk2575.fantasybaseball;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class CSVFile {
	private final String name;
	private final String header;

	@ToString.Exclude
	private final List<String[]> rowsOfDelimitedValues;

	public CSVFile(String name, String content) {
		if (!name.endsWith(".csv")) {
			throw new IllegalArgumentException("File extension is not .csv");
		}
		this.name = name;

		if (!content.contains(",")) {
			throw new IllegalArgumentException("CSV file has no commas");
		}
		String[] split = content.split("\n", 2);
		this.header = split[0].replace("\"", "").replaceAll("\\p{C}", "").trim();

		this.rowsOfDelimitedValues = new ArrayList<>();
		for (String row : split[1].split("\n")) {
			row = row.replace("\"", "").replaceAll("\\p{C}", "").trim();
			this.rowsOfDelimitedValues.add(row.split(","));
		}
	}
}

