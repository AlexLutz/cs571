package edu.emory.mathcs.nlp.component.dep.feature;

import edu.emory.mathcs.nlp.component.dep.DEPFeatureTemplate;
import edu.emory.mathcs.nlp.component.util.feature.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexlutz on 10/5/15.
 */
public class DEPFeatureTemplate3 extends DEPFeatureTemplate {
    @Override
    protected void init() {
        //basic
        add(new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.lemma));

        add(new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.j, 0, Field.lemma));

        //1 gram
        add(new FeatureItem<>(Source.k, 2, Field.lemma));
        add(new FeatureItem<>(Source.i, -1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, -1, Field.lemma));
        add(new FeatureItem<>(Source.i, 1, Field.lemma));
        add(new FeatureItem<>(Source.j, -2, Field.lemma));
        add(new FeatureItem<>(Source.j, -1, Field.lemma));
        add(new FeatureItem<>(Source.j, 1, Field.lemma));
        add(new FeatureItem<>(Source.j, 2, Field.lemma));
        add(new FeatureItem<>(Source.i, -2, Field.pos_tag));
        add(new FeatureItem<>(Source.i, -1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 2, Field.pos_tag));
        add(new FeatureItem<>(Source.j, -1, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 1, Field.pos_tag));

        //2 gram
        add(new FeatureItem<>(Source.j, 1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.k, 2, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.k, 2, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.j, -1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, 1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, 1, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, 1, Field.lemma), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 1, Field.lemma), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, 1, Field.lemma), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, 1, Field.lemma), new FeatureItem<>(Source.j, 0, Field.lemma));



        //3 gram
        add(new FeatureItem<>(Source.i, -2, Field.pos_tag), new FeatureItem<>(Source.i, -1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, -1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.i, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.j, -1, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag), new FeatureItem<>(Source.j, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 0, Field.pos_tag), new FeatureItem<>(Source.j, 1, Field.pos_tag), new FeatureItem<>(Source.j, 2, Field.pos_tag));

        add(new FeatureItem<>(Source.k, 3, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, -1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, -2, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, -1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 1, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 2, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, 3, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        //valency
        add(new FeatureItem<>(Source.i, null, 0, Field.valency, Direction.all), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, null, 0, Field.valency, Direction.all), new FeatureItem<>(Source.j, 0, Field.lemma));

        //2nd order
        add(new FeatureItem<>(Source.i, 0, Field.dependency_label));
        add(new FeatureItem<>(Source.j, 0, Field.dependency_label));
        add(new FeatureItem<>(Source.i, Relation.lmd, 0, Field.dependency_label));

        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, Relation.rmd, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, Relation.lmd, 0, Field.lemma));

        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, Relation.lmd, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lmd, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));


        add(new FeatureItem<>(Source.i, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lmd, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lns, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, Relation.lmd, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        //3rd order
        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.dependency_label));
        add(new FeatureItem<>(Source.j, Relation.h, 0, Field.dependency_label));
        add(new FeatureItem<>(Source.i, Relation.lmd2, 0, Field.dependency_label));

        add(new FeatureItem<>(Source.i, Relation.h2, 0, Field.lemma));
        add(new FeatureItem<>(Source.i, Relation.rmd2, 0, Field.lemma));
        add(new FeatureItem<>(Source.j, Relation.lmd2, 0, Field.lemma));

        add(new FeatureItem<>(Source.i, Relation.h2, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd2, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd2, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.dependency_label), new FeatureItem<>(Source.j, 0, Field.lemma));

        add(new FeatureItem<>(Source.i, Relation.h, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lmd2, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd2, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lns2, 0, Field.dependency_label), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));

        add(new FeatureItem<>(Source.i, Relation.lmd2, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.j, Relation.lmd2, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.rmd2, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.pos_tag));
        add(new FeatureItem<>(Source.i, Relation.lmd2, 0, Field.pos_tag), new FeatureItem<>(Source.i, Relation.lmd, 0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.pos_tag));

        //distributional semantics
//        add(new FeatureItem<>(Source.i, 0, Field.brown));
//        add(new FeatureItem<>(Source.j, 0, Field.brown));
//        add(new FeatureItem<>(Source.i, 1, Field.brown));
//        add(new FeatureItem<>(Source.j, 1, Field.brown));


        // boolean features
        addSet(new FeatureItem<>(Source.i, 0, Field.binary));   //86.60
        addSet(new FeatureItem<>(Source.j, 0, Field.binary));

        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.i, 0, Field.pos_tag), //86.45
                new FeatureItem<>(Source.k, 1, Field.lemma), new FeatureItem<>(Source.k, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.i, 0, Field.pos_tag),
                new FeatureItem<>(Source.k, 1, Field.lemma));
        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.i, 0, Field.pos_tag),
                new FeatureItem<>(Source.k, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.lemma),
                new FeatureItem<>(Source.k, 1, Field.lemma), new FeatureItem<>(Source.k, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.pos_tag),
                new FeatureItem<>(Source.k, 1, Field.lemma), new FeatureItem<>(Source.k, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.k, 1, Field.lemma));
        add(new FeatureItem<>(Source.i, 0, Field.pos_tag), new FeatureItem<>(Source.k, 1, Field.pos_tag));
        add(new FeatureItem<>(Source.k, 1, Field.pos_tag), new FeatureItem<>(Source.j,  0, Field.lemma));

        add(new FeatureItem<>(Source.i, 0, Field.lemma), new FeatureItem<>(Source.i, 0, Field.valency, Direction.right));   //86.14
        add(new FeatureItem<>(Source.i,  0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.valency, Direction.right));
        add(new FeatureItem<>(Source.i,  0, Field.lemma), new FeatureItem<>(Source.i, 0, Field.valency, Direction.left));
        add(new FeatureItem<>(Source.i,  0, Field.pos_tag), new FeatureItem<>(Source.i, 0, Field.valency, Direction.left));
        add(new FeatureItem<>(Source.j,  0, Field.lemma), new FeatureItem<>(Source.j, 0, Field.valency, Direction.left));
        add(new FeatureItem<>(Source.j,  0, Field.pos_tag), new FeatureItem<>(Source.j, 0, Field.valency, Direction.left));

        add(new FeatureItem<>(Source.i, null, 0, Field.subcategory_label, Direction.all));  //
        add(new FeatureItem<>(Source.j, null, 0, Field.subcategory_label, Direction.all));
        add(new FeatureItem<>(Source.i, null, 0, Field.subcategory_lemma, Direction.all));
        add(new FeatureItem<>(Source.j, null, 0, Field.subcategory_lemma, Direction.all));
        add(new FeatureItem<>(Source.i, null, 0, Field.subcategory_pos, Direction.all));
        add(new FeatureItem<>(Source.j, null, 0, Field.subcategory_pos, Direction.all));
    }

}
