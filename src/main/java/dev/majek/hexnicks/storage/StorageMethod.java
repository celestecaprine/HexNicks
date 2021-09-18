/*
 * This file is part of HexNicks, licensed under the MIT License.
 *
 * Copyright (c) 2020-2021 Majekdor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.majek.hexnicks.storage;

import java.util.UUID;

import dev.majek.hexnicks.Nicks;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Handles different storage methods.
 */
public interface StorageMethod {

  /**
   * Check if there is a nickname stored for the provided unique id.
   *
   * @param uuid The unique id to check.
   * @return True if nickname stored.
   */
  boolean hasNick(@NotNull UUID uuid);

  /**
   * Get the nickname stored for a specific unique id. {@link #hasNick(UUID)} should be checked first.
   *
   * @param uuid The unique id to fetch.
   * @return Nickname.
   */
  Component getNick(@NotNull UUID uuid);

  /**
   * Remove a nickname from storage.
   *
   * @param uuid The unique id the nickname is tied to.
   */
  void removeNick(@NotNull UUID uuid);

  /**
   * Save a nickname in storage. This can be used to put it there initially or to update it.
   *
   * @param player The player who's nickname to save.
   */
  void saveNick(@NotNull Player player);

  /**
   * Update the nickname of all online players from storage.
   */
  default void updateNicks() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      Nicks.software().setNick(player, getNick(player.getUniqueId()));
    }
  }
}