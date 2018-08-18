package com.mvp.healthfeed;

public interface Feed {

    Feed EMPTY = new UnknownFeed();

    void accept(Visitor visitor);

    interface Visitor {

        void visit(TipFeed tipFeed);

        void visit(QuizFeed quizFeed);

        void visit(QnaFeed qnaFeed);

        void visit(AdFeed adFeed);

        void visit(UnknownFeed unknownFeed);

    }
}
