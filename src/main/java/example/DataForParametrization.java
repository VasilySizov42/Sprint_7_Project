package example;

public class DataForParametrization {
    private static final String[] BLACK = {"BLACK"};
    private static final String[] GRAY = {"GRAY"};
    private static final String[] BLACK_AND_GRAY = {"BLACK", "GRAY"};
    private static final String[] UNDEFINED_COLOUR = {};

    public static final Object[] BLACK_SCOOTER =
            {"Владимир", "Ульянов",
             "Красная площадь, 9 ", 9,
             "+7-905-917-70-24", 3,
             "2023-10-25",
             "Пролетарии всех стран, объединяйтесь!",
             BLACK, "Чёрный"};
    public static final Object[] GRAY_SCOOTER =
            {"Владимир", "Ульянов",
             "Красная площадь, 9 ", 9,
                    "+7-905-917-70-24", 3,
                    "2023-10-25",
                    "Пролетарии всех стран, объединяйтесь!",
                    GRAY, "Серый"};
    public static final Object[] BLACK_AND_GRAY_SCOOTER =
            {"Владимир", "Ульянов",
                    "Красная площадь, 9 ", 9,
                    "+7-905-917-70-24", 3,
                    "2023-10-25",
                    "Пролетарии всех стран, объединяйтесь!",
                    BLACK_AND_GRAY, "И чёрный, и, в то же время, серый"};
    public static final Object[] UNDEFINED_COLOUR_SCOOTER =
            {"Владимир", "Ульянов",
                    "Красная площадь, 9 ", 9,
                    "+7-905-917-70-24", 3,
                    "2023-10-25",
                    "Пролетарии всех стран, объединяйтесь!",
                    UNDEFINED_COLOUR, "Эээ... Нипанятна"};
}
