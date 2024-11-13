package Konyvek.dao;

import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import Konyvek.Diak;
import Konyvek.Konyv;
import Konyvek.KonyvPeldany;

public class KonyvListaDAOXL implements KonyvListaDAO{
	
	private List<KonyvPeldany> konyvek;
	private String filename;
	
	public KonyvListaDAOXL(String filename)
	{
		this.filename = filename;
		try {
		FileInputStream file = new FileInputStream(new File(this.filename));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		konyvek = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy. MM. dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy. MM. dd");
		for (Row row : sheet) {
			int i = 0;
		    String adatok[] = new String[9];
		    for (Cell cell : row) {
		    	switch (cell.getCellType()) {
	            case STRING: 
	            	adatok[i] = cell.getRichStringCellValue().getString();
	            	break;
	            case NUMERIC: 
	            	if (DateUtil.isCellDateFormatted(cell)) {
	            	   adatok[i] = df.format(cell.getDateCellValue());
	            	} else if(i == 2 || i == 6)
	            	{
	            		adatok[i] = (int)cell.getNumericCellValue() + "";
	            	}
	            	else {
	            	    adatok[i] = cell.getNumericCellValue() + "";
	            	} 
	            	break;
		    	}
		        
				
		    	i++;
		    }
		    Konyv konyv = new Konyv(adatok[0], adatok[1], Integer.parseInt(adatok[2]));
			Diak diak = new Diak(adatok[4], LocalDate.parse(adatok[5], dtf), Integer.parseInt(adatok[6]), adatok[7], adatok[8]);
			KonyvPeldany peldany = new KonyvPeldany(konyv, adatok[3], diak);
			konyvek.add(peldany);
		    
		}
		}
	   catch (FileNotFoundException e) {
		   e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();		
		}
	}
	
	public KonyvPeldany findById(String id)
	{
		for(KonyvPeldany peldany: konyvek)
		{
			if(peldany.getId().equals(id))
			{
				return peldany;
			}
		}
		return null;
	}
	
	public void deletebyID(String id) {
		KonyvPeldany torlendo = null;
		for(KonyvPeldany peldany: konyvek)
		{
			if(peldany.getId().equals(id))
			{
				torlendo = peldany;
				break;
			}
		}
		if(torlendo != null)
		{
			konyvek.remove(torlendo);
		}
		writeFile();
	}
	
	private void writeFile()
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		for(int i = 0; i < konyvek.size(); i++)
		{
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(konyvek.get(i).getKonyv().getCim());
			cell = row.createCell(1);
			cell.setCellValue(konyvek.get(i).getKonyv().getSzerzo());
			cell = row.createCell(2);
			cell.setCellValue(konyvek.get(i).getKonyv().getKiadasEv());
			cell = row.createCell(3);
			cell.setCellValue(konyvek.get(i).getId());
			cell = row.createCell(4);
			cell.setCellValue(konyvek.get(i).getTulajdonos().getNev());
			cell = row.createCell(5);
			cell.setCellValue(konyvek.get(i).getTulajdonos().getSzulev());
			cell = row.createCell(6);
			cell.setCellValue(konyvek.get(i).getTulajdonos().getEvfolyam());
			cell = row.createCell(7);
			cell.setCellValue(konyvek.get(i).getTulajdonos().getAnyaNeve());
			cell = row.createCell(8);
			cell.setCellValue(konyvek.get(i).getTulajdonos().getLakohely());
		}
		try (FileOutputStream outputStream = new FileOutputStream(filename)) {
			workbook.write(outputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void save(KonyvPeldany peldany)
	{
		writeFile();
	}
	
	public List<KonyvPeldany> findAll()
	{
		return konyvek;
	}
}
