package kadai;

import java.util.InputMismatchException;
import java.util.Scanner;

public class kadai4 {

	public static void main(String args[]) {
		TestResult test1 = new TestResult(new EnglishScore(92), new NationalLanguageScore(89), new MathScore(98),
				new ScienceScore(99), new TestTaker("A"));
		TestResult test2 = new TestResult(new EnglishScore(78), new NationalLanguageScore(79), new MathScore(94),
				new ScienceScore(86), new TestTaker("B"));
		TestResult test3 = new TestResult(new EnglishScore(95), new NationalLanguageScore(98), new MathScore(89),
				new ScienceScore(87), new TestTaker("C"));

		TestResults testResults = new TestResults(new TestResult[] { test1, test2, test3 });
		System.out.println("英語：最高点は" + testResults.maxEnglishScore().getEnglishScore() + "最高得点者"
				+ testResults.maxEnglishScoreTestTaker().getTestTakere() + "平均点は" + testResults.aveEnglishScore());
		System.out.println("国語の最高点は" + testResults.maxNationalLanguageScore().getNationalLanguageScore() + "最高得点者"
				+ testResults.maxNationalLanguageScoreTestTaker().getTestTakere() + "平均点は"
				+ testResults.aveNationalLanguageScore());
		System.out.println("数学の最高点は" + testResults.maxMathScore().getMathScore() + "最高得点者"
				+ testResults.maxMathScoreTestTaker().getTestTakere() + "平均点は" + testResults.aveMathScore());
		System.out.println("理科の最高点は" + testResults.maxScienceScore().getScienceScore() + "最高得点者"
				+ testResults.maxScienceScoreTestTaker().getTestTakere() + "平均点は" + testResults.aveScienceScore());

		System.out.println("合計点の" + "最高点は" + testResults.sumMaxScore() + "最高得点者"
				+ testResults.sumMaxScoreTestTaker().getTestTakere() + "平均点は" + testResults.aveSumMaxScore());

		try (Scanner sc = new Scanner(System.in)) {
			String name = input(sc, "名前を入力してください。");
			String subject = input(sc, "教科を入力してください。");

			System.out.println(testResults.searchBestScore(name, subject));
		}

	}

	// 入力チェック
	public static String input(Scanner sc, String msg) {
		while (true) {
			System.out.println(msg);
			try {
				return sc.nextLine();
			} catch (InputMismatchException e) {
				sc.next();
			}
		}
	}
}

class EnglishScore {
	private final int val;

	public EnglishScore(int val) {
		this.val = val;
	}

	public int getEnglishScore() {
		return this.val;
	}
}

class NationalLanguageScore {
	private final int val;

	public NationalLanguageScore(int val) {
		this.val = val;
	}

	public int getNationalLanguageScore() {
		return this.val;
	}
}

class ScienceScore {
	private final int val;

	public ScienceScore(int val) {
		this.val = val;
	}

	public int getScienceScore() {
		return this.val;
	}
}

class MathScore {
	private final int val;

	public MathScore(int val) {
		this.val = val;
	}

	public int getMathScore() {
		return this.val;
	}
}

class TestTaker {
	private final String val;

	public TestTaker(String testTaker) {
		this.val = testTaker;
	}

	public String getTestTakere() {
		return this.val;
	}
}

class TestResult {
	public EnglishScore englishScore; // 英語スコア
	public NationalLanguageScore nationalLanguageScore; // 国語スコア
	public MathScore mathScore; // 数学スコア
	public ScienceScore scienceScore; // 理科スコア
	public TestTaker testTaker; // 受験者

	public TestResult(EnglishScore englishScore, NationalLanguageScore nationalLanguageScore, MathScore mathScore,
			ScienceScore scienceScore, TestTaker testTaker) {
		this.englishScore = englishScore;
		this.nationalLanguageScore = nationalLanguageScore;
		this.mathScore = mathScore;
		this.scienceScore = scienceScore;
		this.testTaker = testTaker;
	}

	// 合計点計算
	public int sumScore() {
		return this.englishScore.getEnglishScore() + this.nationalLanguageScore.getNationalLanguageScore()
				+ this.mathScore.getMathScore() + this.scienceScore.getScienceScore();
	}
}

class TestResults {
	public TestResult[] testResults;

	public TestResults(TestResult[] testResults) {
		if (testResults.length <= 0) {
			throw new IllegalArgumentException("配列の要素がありません。");
		}
		this.testResults = testResults;
	}
	
	//4-2課題用メソッド
	public String searchBestScore(String testTaker, String subject) {
		// 引数の受験者が存在するかチェック
		boolean existTestTaker = false;
		int index = 0;
		for (int i = 0; i < this.testResults.length; i++) {
			if (this.testResults[i].testTaker.getTestTakere().equals(testTaker)) {
				existTestTaker = true;
				index = 0;
			}
		}

		if (!existTestTaker) {
			throw new IllegalArgumentException("存在しない受験者です");
		}

		if (subject.equals("英語")) {
			if (testTaker.equals(maxEnglishScoreTestTaker().getTestTakere())) {
				return "あなたは英語で最高得点者です";
			}
			return "英語の最高得点まで"
					+ (maxEnglishScore().getEnglishScore() - this.testResults[index].englishScore.getEnglishScore());
		} else if (subject.equals("国語")) {
			if (testTaker.equals(maxNationalLanguageScoreTestTaker().getTestTakere())) {
				return "あなたは国語で最高得点者です";
			}
			return "国語の最高得点まで" + (maxNationalLanguageScore().getNationalLanguageScore()
					- this.testResults[index].nationalLanguageScore.getNationalLanguageScore());
		} else if (subject.equals("数学")) {
			if (testTaker.equals(maxMathScoreTestTaker().getTestTakere())) {
				return "あなたは数学で最高得点者です";
			}
			return "数学の最高得点まで" + (maxMathScore().getMathScore() - this.testResults[index].mathScore.getMathScore());
		} else if (subject.equals("理科")) {
			if (testTaker.equals(maxScienceScoreTestTaker().getTestTakere())) {
				return "あなたは理科で最高得点者です";
			}
			return "理科の最高得点まで"
					+ (maxScienceScore().getScienceScore() - this.testResults[index].scienceScore.getScienceScore());
		} else if (subject.equals("全教科")) {
			if (testTaker.equals(sumMaxScoreTestTaker().getTestTakere())) {
				return "あなたは全教科の合計点で最高得点者です";
			}
			return "全教科の合計点の最高得点まで" + (sumMaxScore() - this.testResults[index].sumScore());
		} else {
			throw new IllegalArgumentException("存在しない教科です");
		}
	}

	//合計点の最大を求める
	public int sumMaxScore() {
		int max = 0;
		for (int i = 0; i < this.testResults.length; i++) {
			if (max < testResults[i].sumScore()) {
				max = testResults[i].sumScore();
			}
		}
		return max;
	}

	//合計点の平均点を求める
	public int aveSumMaxScore() {
		int len = this.testResults.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += this.testResults[i].sumScore();
		}
		return sum / len;
	}

	//合計点の最大得点者を求める
	public TestTaker sumMaxScoreTestTaker() {
		int len = this.testResults.length;
		int max = 0;
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (max < testResults[i].sumScore()) {
				index = 0;
			}
		}
		return this.testResults[index].testTaker;
	}

	public EnglishScore maxEnglishScore() {
		int len = this.testResults.length;
		EnglishScore max = this.testResults[0].englishScore;
		for (int i = 0; i < len; i++) {
			if (max.getEnglishScore() < testResults[i].englishScore.getEnglishScore()) {
				max = testResults[i].englishScore;
			}
		}
		return max;
	}

	public int aveEnglishScore() {
		int len = this.testResults.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += this.testResults[i].englishScore.getEnglishScore();
		}
		return sum / len;
	}

	public TestTaker maxEnglishScoreTestTaker() {
		int len = this.testResults.length;
		int max = this.testResults[0].englishScore.getEnglishScore();
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (max < testResults[i].englishScore.getEnglishScore()) {
				index = i;
			}
		}
		return this.testResults[index].testTaker;
	}

	public NationalLanguageScore maxNationalLanguageScore() {
		int len = this.testResults.length;
		NationalLanguageScore max = this.testResults[0].nationalLanguageScore;
		for (int i = 0; i < len; i++) {
			if (max.getNationalLanguageScore() < testResults[i].nationalLanguageScore.getNationalLanguageScore()) {
				max = testResults[i].nationalLanguageScore;
			}
		}
		return max;
	}

	public int aveNationalLanguageScore() {
		int len = this.testResults.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += this.testResults[i].nationalLanguageScore.getNationalLanguageScore();
		}
		return sum / len;
	}

	public TestTaker maxNationalLanguageScoreTestTaker() {
		int len = this.testResults.length;
		int max = this.testResults[0].nationalLanguageScore.getNationalLanguageScore();
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (max < testResults[i].nationalLanguageScore.getNationalLanguageScore()) {
				index = i;
			}
		}
		return this.testResults[index].testTaker;
	}

	public MathScore maxMathScore() {
		int len = this.testResults.length;
		MathScore max = this.testResults[0].mathScore;
		for (int i = 0; i < len; i++) {
			if (max.getMathScore() < testResults[i].mathScore.getMathScore()) {
				max = testResults[i].mathScore;
			}
		}
		return max;
	}

	public int aveMathScore() {
		int len = this.testResults.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += this.testResults[i].mathScore.getMathScore();
		}
		return sum / len;
	}

	public TestTaker maxMathScoreTestTaker() {
		int len = this.testResults.length;
		int max = this.testResults[0].mathScore.getMathScore();
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (max < testResults[i].mathScore.getMathScore()) {
				index = i;
			}
		}
		return this.testResults[index].testTaker;
	}

	public ScienceScore maxScienceScore() {
		int len = this.testResults.length;
		ScienceScore max = this.testResults[0].scienceScore;
		for (int i = 0; i < len; i++) {
			if (max.getScienceScore() < testResults[i].scienceScore.getScienceScore()) {
				max = testResults[i].scienceScore;
			}
		}
		return max;
	}

	public int aveScienceScore() {
		int len = this.testResults.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += this.testResults[i].scienceScore.getScienceScore();
		}
		return sum / len;	
	}

	public TestTaker maxScienceScoreTestTaker() {
		int len = this.testResults.length;
		int max = this.testResults[0].scienceScore.getScienceScore();
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (max < testResults[i].scienceScore.getScienceScore()) {
				index = i;
			}
		}
		return this.testResults[index].testTaker;
	}
}
