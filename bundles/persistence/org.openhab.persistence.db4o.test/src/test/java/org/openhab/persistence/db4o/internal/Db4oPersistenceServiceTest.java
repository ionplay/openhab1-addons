/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.persistence.db4o.internal;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openhab.persistence.db4o.internal.Db4oPersistenceService.BackupJob;

import junit.framework.Assert;

/**
 * @author Thomas.Eichstaedt-Engelen
 * @since 1.0.0
 */
public class Db4oPersistenceServiceTest {

    private BackupJob backupJob;
    private String[] backupFileNames = new String[] { "20120802173012", "20120701134442", "20120801191254",
            "20120805172351", "20120803095643" };
    private int[] expectedResultIndexes = new int[] { 0, 4, 3 };

    @Before
    public void init() {
        Db4oConfiguration.maxBackups = 3;
        backupJob = new Db4oPersistenceService.BackupJob();
    }

    @Test
    public void testRemoveObsoleteBackupFiles() throws IOException {
        String testDbDirName = "./target/etc/db4o/";

        File db4oDir = new File(testDbDirName);
        db4oDir.mkdirs();
        FileUtils.cleanDirectory(db4oDir);

        for (int index = 0; index < backupFileNames.length; index++) {
            new File(testDbDirName + backupFileNames[index] + "_store.db4o.bak").createNewFile();
        }
        Assert.assertEquals(backupFileNames.length, db4oDir.listFiles().length);

        // Method under Test
        backupJob.removeObsoleteBackupFiles(testDbDirName);

        // Expected results ...
        File[] result = db4oDir.listFiles();
        Arrays.sort(result);

        Assert.assertEquals(Db4oConfiguration.maxBackups, result.length);
        for (int index = 0; index < result.length; index++) {
            Assert.assertEquals(backupFileNames[expectedResultIndexes[index]] + "_store.db4o.bak",
                    result[index].getName());
        }
    }

}
