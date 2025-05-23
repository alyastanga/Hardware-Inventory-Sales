/** 
 *  Copyright 2025 Aaron Ragudos, Hanz Mapua, Peter Dela Cruz, Jerick Remo, Kurt Raneses, and the contributors of the project.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”),
 *  to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.hanzm_10.murico.swingapp.lib.database.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.jetbrains.annotations.NotNull;

import com.github.hanzm_10.murico.swingapp.lib.auth.MuricoCrypt.HashedStringWithSalt;

public interface AccountDao {
	public HashedStringWithSalt getHashedPasswordWithSaltByUserDisplayName(@NotNull final String displayName)
			throws IOException, SQLException;

	public boolean isEmailTaken(@NotNull final String email) throws IOException, SQLException;

	/**
	 * Starts a transaction and handles two side effects
	 *
	 * <ul>
	 * <li>Creates a row in {@code users} table
	 * <li>Creates a row in {@code accounts_pending_verifications} table
	 * </ul>
	 *
	 * <p>
	 * These are necessary to prevent stale data. If one operation fails, a
	 * transaction can roll back previous insertions/creations.
	 */
	public void registerAccount(@NotNull final String displayName, @NotNull final String email,
			@NotNull final HashedStringWithSalt hashedPassword) throws IOException, SQLException;
}
