package org.java.new_features.record;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Student st = new Student("John", 70, LocalDate.now());

        st.age();

    }
}
