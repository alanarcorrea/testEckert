package util;

import org.openqa.selenium.By;

public class Finders {
	public static By tableNormalFinder = By.cssSelector(".table-normal");
	public static By tableRowsFinder = By.cssSelector("tbody>tr");
	public static By emptyCell = By.cssSelector(".empty-cell");
	public static By textAreaFinder = By.cssSelector("textarea");
	public static By td = By.cssSelector("td");
	public static By th = By.cssSelector("th");
	public static By span = By.cssSelector("span");
	public static By input = By.cssSelector("input");
	public static By label = By.cssSelector(".label");
	public static By pureLabel = By.cssSelector("label");
	public static By imgFinder = By.cssSelector("img");
	public static By fwBoldFinder = By.cssSelector(".fw-bold");

	public static By buttonExport = By.cssSelector(".btn-template-export");
	public static By buttonListTemplate = By.cssSelector(".btn-template-view");
	public static By templateManagerButton = By.cssSelector(".btn-template-edit");
	public static By buttonEdit = By.cssSelector(".btn-edit");
	public static By li = By.cssSelector("li");
	public static By p = By.cssSelector("p");
	public static By a = By.cssSelector("a");
	public static By nameById = By.id("name");
	public static By templatePageContainer = By.cssSelector(".template-page");
	public static By btnDown = By.cssSelector(".btn-down");
	public static By btnUp = By.cssSelector(".btn-up");
	public static By btnOk = By.cssSelector(".btn-ok");
	public static By buttonSelectFinder = By.cssSelector(".btn-select");
	public static By buttonUnselectFinder = By.cssSelector(".btn-unselect");
	public static By buttonAddFinder = By.cssSelector(".btn-add");
	public static By buttonListFinder = By.cssSelector(".btn-list");
	public static By buttonDeleteFinder = By.cssSelector(".btn-delete");
	public static By buttonCancel = By.cssSelector(".btn-cancel");
	public static By buttonClose = By.cssSelector(".btn-close");
	public static By buttonSave = By.cssSelector(".btn-save");
	public static By buttonReset = By.cssSelector(".btn-reset");
	public static By tableHeaders = By.cssSelector("thead>th");
	public static By div = By.cssSelector("div");
	public static By bold = By.cssSelector("b");
	public static By searchButtonByName = By.name("searchButton");
	public static By nameFieldByName = By.name("name");
	public static By urlFieldByName = By.name("url");
	public static By dropdownValueContent = By.cssSelector(".DropdownValueContent");
	public static By buttonAddDecision = By.id("btAddDecision");
	public static By paginationButtonFinder = By.cssSelector(".pagination .pages a");
	public static By copyFromButtonFinder = By.id("btCopyFrom");
	public static By popupFooterFinder = By.cssSelector(".popup-footer");
}
