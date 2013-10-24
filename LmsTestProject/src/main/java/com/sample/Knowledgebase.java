package com.sample;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder; 
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class Knowledgebase {

	public static KnowledgeBase kbase;
	public StatefulKnowledgeSession ksession;	
	private static KnowledgeBuilder kbuilder;
	
	public Knowledgebase(String job)
	{
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		try {
            // load up the knowledge base
            kbase = Knowledgebase.readKnowledgeBase(job);
            
            ksession = kbase.newStatefulKnowledgeSession(); 
    		ksession.getWorkItemManager().registerWorkItemHandler("WebService", new WebServiceHandler());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	public void AddFlow(String job)
	{
        kbuilder.add(ResourceFactory.newClassPathResource(job), ResourceType.DRF); 
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());		
	}

    public static KnowledgeBase readKnowledgeBase(String job) throws Exception {
        kbuilder.add(ResourceFactory.newClassPathResource(job), ResourceType.DRF); 
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}
