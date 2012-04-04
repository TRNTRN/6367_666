/*
 * Copyright 2011 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.report;

import static org.pitest.mutationtest.report.Tag.index;
import static org.pitest.mutationtest.report.Tag.killingTest;
import static org.pitest.mutationtest.report.Tag.lineNumber;
import static org.pitest.mutationtest.report.Tag.mutatedClass;
import static org.pitest.mutationtest.report.Tag.mutatedMethod;
import static org.pitest.mutationtest.report.Tag.mutation;
import static org.pitest.mutationtest.report.Tag.mutator;
import static org.pitest.mutationtest.report.Tag.sourceFile;

import java.io.IOException;
import java.io.Writer;

import org.pitest.Description;
import org.pitest.TestResult;
import org.pitest.extension.TestListener;
import org.pitest.functional.Option;
import org.pitest.mutationtest.MutationDetails;
import org.pitest.mutationtest.instrument.MutationMetaData;
import org.pitest.mutationtest.results.MutationResult;
import org.pitest.util.StringUtil;
import org.pitest.util.Unchecked;

enum Tag {
  mutation, sourceFile, mutatedClass, mutatedMethod, lineNumber, mutator, index, killingTest;
}

public class XMLReportListener implements TestListener {

  private final Writer out;

  public XMLReportListener(final ResultOutputStrategy outputStrategy) {
    this(outputStrategy.createWriterForFile("mutations.xml"));
  }

  public XMLReportListener(final Writer out) {
    this.out = out;
  }

  public void onRunStart() {
    write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    write("<mutations>\n");
  }

  public void onTestFailure(final TestResult tr) {
    writeResult(tr);
  }

  public void onTestError(final TestResult tr) {
    writeResult(tr);
  }

  public void onTestSkipped(final TestResult tr) {
  }

  public void onTestSuccess(final TestResult tr) {
    writeResult(tr);
  }

  private void writeResult(final TestResult tr) {

    for (final MutationMetaData metaData : extractMetaData(tr)) {
      for (final MutationResult mutation : metaData.getMutations()) {
        writeMutationResultXML(mutation);
      }
    }
  }

  private void writeMutationResultXML(final MutationResult result) {
    write(makeNode(makeMutationNode(result), makeMutationAttributes(result),
        mutation) + "\n");
  }

  private String makeMutationAttributes(final MutationResult result) {
    return "detected='" + result.getStatus().isDetected() + "' status='"
        + result.getStatus() + "'";
  }

  private String makeMutationNode(final MutationResult mutation) {
    final MutationDetails details = mutation.getDetails();
    return makeNode(clean(details.getFilename()), sourceFile)
        + makeNode(clean(details.getClazz()), mutatedClass)
        + makeNode(clean(details.getMethod()), mutatedMethod)
        + makeNode("" + details.getLineNumber(), lineNumber)
        + makeNode(clean(details.getId().getMutator()), mutator)
        + makeNode("" + details.getId().getIndex(), index)
        + makeNode(createKillingTestDesc(mutation.getKillingTest()),
            killingTest);
  }

  private String clean(final String value) {
    return StringUtil.escapeBasicHtmlChars(value);
  }

  private String makeNode(final String value, final String attributes,
      final Tag tag) {
    if (value != null) {
      return "<" + tag + " " + attributes + ">" + value + "</" + tag + ">";
    } else {
      return "<" + tag + attributes + "/>";
    }

  }

  private String makeNode(final String value, final Tag tag) {
    if (value != null) {
      return "<" + tag + ">" + value + "</" + tag + ">";
    } else {
      return "<" + tag + "/>";
    }
  }

  private String createKillingTestDesc(final Option<String> killingTest) {
    if (killingTest.hasSome()) {
      return clean(killingTest.value());
    } else {
      return null;
    }
  }

  private Option<MutationMetaData> extractMetaData(final TestResult tr) {
    return tr.getValue(MutationMetaData.class);

  }

  private void write(final String value) {
    try {
      this.out.write(value);
    } catch (final IOException e) {
      throw Unchecked.translateCheckedException(e);
    }
  }

  public void onRunEnd() {
    try {
      write("</mutations>\n");
      this.out.close();
    } catch (final IOException e) {
      throw Unchecked.translateCheckedException(e);
    }
  }

  public void onTestStart(final Description d) {
  }

}