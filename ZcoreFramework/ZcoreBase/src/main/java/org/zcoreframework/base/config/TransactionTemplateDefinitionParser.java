package org.zcoreframework.base.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Element;

/**
 * @author Ali Karimizandi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */
public class TransactionTemplateDefinitionParser implements BeanDefinitionParser {

    private static final String PREFIX = "org.zcoreframework.base.";

    public static final String TRANSACTION_TEMPLATE = PREFIX + "transactionTemplate";

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // get source
        Object source = parserContext.extractSource(element);

        // transaction template
        if (!parserContext.getRegistry().containsBeanDefinition(TRANSACTION_TEMPLATE)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(TransactionTemplate.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // should be prototype
            beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);

            // constructor argument
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, new RuntimeBeanReference(DataAccessDefinitionParser.TRANSACTION_MANAGER));
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // set propagation behavior
            int propagationBehavior;
            switch (element.getAttribute("propagation").toUpperCase()) {
                case "REQUIRES_NEW":
                    propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRES_NEW;
                case "REQUIRED":
                    propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED;
                case "NESTED":
                    propagationBehavior = TransactionDefinition.PROPAGATION_NESTED;
                case "MANDATORY":
                    propagationBehavior = TransactionDefinition.PROPAGATION_MANDATORY;
                case "NEVER":
                    propagationBehavior = TransactionDefinition.PROPAGATION_NEVER;
                case "SUPPORTS":
                    propagationBehavior = TransactionDefinition.PROPAGATION_SUPPORTS;
                case "NOT_SUPPORTED":
                    propagationBehavior = TransactionDefinition.PROPAGATION_NOT_SUPPORTED;
                default:
                    propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRES_NEW;
            }
            beanDefinition.getPropertyValues().add("propagationBehavior", propagationBehavior);

            // register
            parserContext.getRegistry().registerBeanDefinition(TRANSACTION_TEMPLATE, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, TRANSACTION_TEMPLATE));

            // log
            logger.info("register " + TRANSACTION_TEMPLATE + " bean definition.");
        }

        return null;
    }
}
