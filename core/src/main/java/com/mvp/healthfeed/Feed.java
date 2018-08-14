package com.mvp.healthfeed;

public interface Feed {

    Feed EMPTY = new UnknownFeed();

    void accept(Visitor visitor);

    interface Visitor {

        void visit(HealthTipFeed tipFeed);

        void visit(HealthQuizFeed healthQuizFeed);

        void visit(HealthQnaFeed healthQnaFeed);

        void visit(HealthAdFeed healthAdFeed);

        void visit(UnknownFeed unknownFeed);

    }
}
