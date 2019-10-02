package org.zcoreframework.base.gateway.ftp.iml;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.model.file.Document;
import org.zcoreframework.base.gateway.model.file.HeadInfo;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;
import org.zcoreframework.base.gateway.serialization.file.CsvRecordFormatter;
import org.zcoreframework.base.gateway.serialization.file.CsvRecordParser;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class CsvSessionImpl extends AbstractDocumentSessionImpl {

    public CsvSessionImpl(ServiceInfo serviceInfo) throws GatewayException {
        super(serviceInfo);
    }

    @Override
    public <M extends Document> M readData(HeadInfo headInfo, Class<M> clazz) throws GatewayException {
        M document = null;

        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();

        if (headInfo.isUnknownHeader()) {
            format = format.withSkipHeaderRecord();
        }

        try {
            document = clazz.newInstance();

            document.setHeadInfo(headInfo);
            document.setEntries(new ArrayList());

            CsvRecordParser rp = new CsvRecordParser(document.getEntryType());

            InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(read(headInfo.getResource())));
            CSVParser csvParser = new CSVParser(reader, format);

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                document.getEntries().add(rp.apply(csvRecord.getRecordNumber(), csvRecord));
            }

            csvParser.close();

        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new GatewayException("_TODO", e);
        }

        return document;
    }

    @Override
    public <M extends Document> boolean saveData(M document) throws GatewayException {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();

        if (document.getHeadInfo().isUnknownHeader()) {
            format = format.withSkipHeaderRecord();
        }

        try {
            CsvRecordFormatter rf = new CsvRecordFormatter(document.getEntryType());

            CSVPrinter printer = new CSVPrinter(writer, format);

            for (Object model : document.getEntries()) {
                printer.printRecord(rf.apply(model));
            }

            printer.flush();
            printer.close();
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        }

        try {
            IOUtils.write(writer.toString(), getClient().storeFileStream(document.getHeadInfo().getResource()));
            return true;
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        }
    }


}
