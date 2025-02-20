/*
 * Copyright Besu Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 */
package org.hyperledger.besu.ethereum.trie.verkle.node;

import org.hyperledger.besu.ethereum.trie.verkle.visitor.NodeVisitor;
import org.hyperledger.besu.ethereum.trie.verkle.visitor.PathNodeVisitor;

import java.util.Optional;

import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;

/**
 * A special node representing a null or empty node in the Verkle Trie.
 *
 * <p>The `NullNode` class serves as a placeholder for non-existent nodes in the Verkle Trie
 * structure. It implements the Node interface and represents a node that contains no information or
 * value.
 */
public class NullLeafNode<V> implements Node<V> {
  @SuppressWarnings("rawtypes")
  private static final NullLeafNode instance = new NullLeafNode();

  /**
   * Constructs a new `NullNode`. This constructor is protected to ensure that `NullNode` instances
   * are only created as singletons.
   */
  protected NullLeafNode() {}

  /**
   * Gets the shared instance of the `NullNode`.
   *
   * @param <V> The type of the node's value.
   * @return The shared `NullNode` instance.
   */
  @SuppressWarnings("unchecked")
  public static <V> NullLeafNode<V> instance() {
    return instance;
  }

  /**
   * Accepts a visitor for path-based operations on the node.
   *
   * @param visitor The path node visitor.
   * @param path The path associated with a node.
   * @return The result of the visitor's operation.
   */
  @Override
  public Node<V> accept(final PathNodeVisitor<V> visitor, final Bytes path) {
    return visitor.visit(this, path);
  }

  /**
   * Accepts a visitor for generic node operations.
   *
   * @param visitor The node visitor.
   * @return The result of the visitor's operation.
   */
  @Override
  public Node<V> accept(final NodeVisitor<V> visitor) {
    return visitor.visit(this);
  }

  /**
   * Get the hash associated with the `NullNode`.
   *
   * @return An optional containing the empty hash.
   */
  @Override
  public Optional<Bytes32> getHash() {
    return Optional.of(EMPTY_HASH);
  }

  /**
   * Get the hash associated with the `NullNode`.
   *
   * @return An optional containing the empty hash.
   */
  @Override
  public Optional<Bytes32> getCommitment() {
    return Optional.of(EMPTY_COMMITMENT);
  }

  /**
   * Get a string representation of the `NullNode`.
   *
   * @return A string representation indicating that it is a "NULL" node.
   */
  @Override
  public String print() {
    return "[NULL-LEAF]";
  }

  /**
   * Check if the `NullNode` is marked as dirty (needing to be persisted).
   *
   * @return `false` since a `NullNode` does not require persistence.
   */
  @Override
  public boolean isDirty() {
    return false;
  }

  /**
   * Mark the `NullNode` as dirty (not used, no operation).
   *
   * <p>This method intentionally does nothing.
   */
  @Override
  public void markDirty() {
    // do nothing
  }
}
