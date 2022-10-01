import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Advertisement {
	private String header;
	private String text;
	private String author;
	private LocalDateTime date;
	private int like;
	private int dislike;

	
	public Advertisement(String header, String text, String author) {
		this.header = header;
		this.text = text;
		this.author = author;
		this.date = LocalDateTime.now();
		this.like = 0;
		this.dislike = 0;
	}
	
	public String getAuthor() {
		return author;
	}

	public int getLike() {
		return like;
	}

	public int getDislike() {
		return dislike;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

		stringBuilder.append("<div class='board-advertisement'>");
		stringBuilder.append("<div class='board-advertisement-header'>");
		stringBuilder.append("<div class='board-advertisement-header-header'>").append(this.header).append("</div>");
		stringBuilder.append("<div class='board-advertisement-header-author'>").append(this.author).append(", ").append(this.date.format(formatter)).append("</div>");
		stringBuilder.append("</div>");
		stringBuilder.append("<div class='board-advertisement-text'>").append(this.text).append("</div>");

		stringBuilder.append("<form method=\"POST\" action=\"/board/board\">\n");
		stringBuilder.append("<button class=\"button\" type=\"submit\" name=\"sort\" value=\"like\">Like</button>\n");
		stringBuilder.append("</form>\n");


//		id="foo" onclick="likeIncrease()"
//		stringBuilder.append("<script>\n" +
//                    "foo.addEventListener('click', function())\n" +
//                    "function likeIncrease() {" +
//                     +
//                    "}\n" +
//                    "</script>");
		stringBuilder.append("<button class=\"button\" type=\"submit\" name=\"sort\" value=\"dislike\">Dislike</button>\n");
		stringBuilder.append("</div>");
		return stringBuilder.toString();
	}
}
