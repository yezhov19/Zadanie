import java.util.*;


    class main {
        static Scanner sc = new Scanner(System.in);
        static String[] s = new String[3];
        static Integer[] v = new Integer[3];

        static int resArab;
        static String resRim;

        final static List<String> rimList = new ArrayList<>() {{
            add("I");
            add("II");
            add("III");
            add("IV");
            add("V");
            add("VI");
            add("VII");
            add("VIII");
            add("IX");
            add("X");
        }};

        final static Map<String, Integer> rimMap = new LinkedHashMap<>() {{
            put("C", 100);
            put("XC", 90);
            put("LXXX", 80);
            put("LXX", 70);
            put("LX", 60);
            put("L", 50);
            put("XL", 40);
            put("XXX", 30);
            put("XX", 20);
            put("X", 10);
            put("IX", 9);
            put("VIII", 8);
            put("VII", 7);
            put("VI", 6);
            put("V", 5);
            put("IV", 4);
            put("III", 3);
            put("II", 2);
            put("I", 1);
        }};

        public static void main(String[] args) throws Exception {

            String input = sc.nextLine();

            if (calc(input) == null) {
                throw new Exception("Ð ÐµÐ·ÑÐ»ÑÑÐ°Ñ ÑÐ¸Ð¼ÑÐºÐ¸Ñ Ð²ÑÑÐ¸ÑÐ»ÐµÐ½Ð¸Ð¹ Ð½Ðµ Ð´Ð¾Ð»Ð¶ÐµÐ½ Ð±ÑÑÑ Ð¼ÐµÐ½ÐµÐµ I");
            } else {
                System.out.println(calc(input));
            }
        }

        public static void arabCalc() {
            for (int i = 0; i < 3; i++) {
                if (i == 0 || i == 2) {
                    v[i] = Integer.parseInt(s[i]);
                }
            }

            getCase();
        }

        public static void rimCalc() throws Exception {
            if (!rimMap.containsKey(s[0]) || !rimMap.containsKey(s[2])) {
                throw new Exception("ÐÐ¾Ð¿ÑÑÑÐ¸Ð¼Ñ Ð·Ð½Ð°ÑÐµÐ½Ð¸Ñ Ð¾Ñ I Ð´Ð¾ X");
            }
            for (int i = 0; i < 3; i++) {
                for (Map.Entry<String, Integer> entry : rimMap.entrySet()) {
                    if (s[i].equals(entry.getKey())) {
                        v[i] = entry.getValue();
                    }
                }
            }

            getCase();

            for (Map.Entry<String, Integer> entry : rimMap.entrySet()) {
                if (resArab >= 10) {
                    if (resArab - resArab % 10 == entry.getValue()) {
                        resRim = entry.getKey();
                    }
                    if (resArab % 10 == entry.getValue()) {
                        resRim += entry.getKey();
                    }
                } else {
                    if (resArab == entry.getValue()) {
                        resRim = entry.getKey();
                    }
                }
            }
        }

        private static void getCase() {
            switch (s[1]) {
                case "+" -> resArab = v[0] + v[2];
                case "-" -> resArab = v[0] - v[2];
                case "/" -> resArab = v[0] / v[2];
                case "*" -> resArab = v[0] * v[2];
            }
        }

        public static String calc(String input) throws Exception {
            s = input.split(" ");

            if (s.length > 3) {
                throw new Exception("ÐÐµÐ²ÐµÑÐ½Ð¾Ðµ Ð²ÑÑÐ°Ð¶ÐµÐ½Ð¸Ðµ. ÐÐ¾Ð¶Ð½Ð¾ Ð²Ð²ÐµÑÑÐ¸ ÑÐ¾Ð»ÑÐºÐ¾ Ð´Ð²Ð° ÑÐ¸ÑÐ»Ð°. ÐÑÐ¸Ð¼ÐµÑ: 1 + 4, X - II");
            }

            if (rimList.contains(s[2]) && rimList.contains(s[0])) {
                rimCalc();
                return resRim;
            } else {
                arabCalc();
                return String.valueOf(resArab);
            }
        }

    }
