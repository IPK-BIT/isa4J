package isa4J;

import java.io.IOException;
import java.net.URL;

import de.ipk_gatersleben.bit.bi.isa4j.Investigation;
import de.ipk_gatersleben.bit.bi.isa4j.Study;
import de.ipk_gatersleben.bit.bi.isa4j.components.Comment;
import de.ipk_gatersleben.bit.bi.isa4j.components.Commentable;
import de.ipk_gatersleben.bit.bi.isa4j.components.Contact;
import de.ipk_gatersleben.bit.bi.isa4j.components.DesignDescriptor;
import de.ipk_gatersleben.bit.bi.isa4j.components.Ontology;
import de.ipk_gatersleben.bit.bi.isa4j.components.OntologyAnnotation;
import de.ipk_gatersleben.bit.bi.isa4j.components.Publication;

public class Playground {

	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException {		
		Investigation investigation = new Investigation("investigation_id");
		
		Ontology ontology1 = new Ontology("UO", new URL("http://data.bioontology.org/ontologies/UO"), null, "Description of lala");
		Ontology ontology2 = new Ontology("CRediT", new URL("http://purl.org/credit/ontology#"), "1.40", "Description of CrEDIT");
		investigation.addOntology(ontology1);
		investigation.addOntology(ontology2);
		
		investigation.addComment(new Comment("Investigation Type", "Value"));
		investigation.addComment(new Comment("Another Comment", "Hello"));
		
		Contact karlheinz = new Contact("Schmidt", "Karl-Heinz", "khschmitt@wunderland.de", "Schmidt GmbH", "Schmidtstraße 1, 543423 Schmidttown");
		karlheinz.setFax("FAxi1223");
		Contact ursel = new Contact("Wurst", "Ursel", null, "Wurstwaren Ursel", null);
		OntologyAnnotation role1 = new OntologyAnnotation();
		role1.setName("Terminator");
		role1.setSourceREF(ontology2);
		role1.setTermAccessionNumber("ACredIT#1321");
		ursel.setRolesOntology(role1);
		ursel.addComment(new Comment("Person REF", "urselRef"));
		ursel.addComment(new Comment("Ursel says", "Bye bye World"));
		
		OntologyAnnotation role2 = new OntologyAnnotation();
		role2.setName("Secret Role");
		karlheinz.setRolesOntology(role2);
		karlheinz.addComment(new Comment("Person REF", "karlheinzREf"));
		karlheinz.addComment(new Comment("Karlheinz says", "Hello World!"));
		
		Publication paper1 = new Publication();
		paper1.addAuthor(karlheinz);
		paper1.addAuthor(ursel);
		paper1.setTitle("Wurst und Utilitarismus");
		paper1.setDOI("doi.org/ursel.wurst12321");
		
		Publication paper2 = new Publication();
		paper2.addAuthor(karlheinz);
		paper2.setTitle("Hello World");
		paper2.setPubmedID("pubmedio.com");
		
		investigation.addPublication(paper1);
		investigation.addPublication(paper2);
		
		OntologyAnnotation paper1Status = new OntologyAnnotation();
		paper1Status.setSourceREF(ontology1);
		paper1Status.setName("Published");
		paper1Status.setTermAccessionNumber("Term Accession alala");
		paper1.setStatusOntology(paper1Status);
		
		investigation.addContact(ursel);
		investigation.addContact(karlheinz);
		
		Study study1 = new Study("Study #1", "s_study1.txt");
		Study study2 = new Study("Study #2", "s_study2.txt");
		
		study1.setTitle("A super great Study");
		study2.setDescription("This one is even greater");
		study1.addComment(new Comment("Comment1", "Value"));
		study1.addComment(new Comment("Comment 2", "value"));
		
		study2.addDesignDescriptor(new DesignDescriptor("Simple"));
		study2.addDesignDescriptor(new DesignDescriptor(paper1Status));
		
		investigation.addStudy(study1);
		investigation.addStudy(study2);
		
		
		investigation.writeToFile("test.txt");
	}

}